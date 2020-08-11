package com.mindtree.cartservice.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.cartservice.dto.CartDto;
import com.mindtree.cartservice.dto.ProductDto;
import com.mindtree.cartservice.entity.Cart;
import com.mindtree.cartservice.entity.Product;
import com.mindtree.cartservice.exception.service.InvalidProductQuantityException;
import com.mindtree.cartservice.exception.service.NoCartExistsException;
import com.mindtree.cartservice.exception.service.NoItemsToDeleteException;
import com.mindtree.cartservice.exception.service.NoItemsToDisplayException;
import com.mindtree.cartservice.exception.service.NoSuchProduct;
import com.mindtree.cartservice.exception.service.ProductAlreadyInCartException;
import com.mindtree.cartservice.exception.service.ServiceException;
import com.mindtree.cartservice.repository.CartRepository;
import com.mindtree.cartservice.repository.ProductRepository;
import com.mindtree.cartservice.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartRepository cartRepository;

	@Autowired
	ProductRepository productRepository;

	@Override
	public CartDto addToCart(Set<ProductDto> productsDto, String userId) throws ServiceException {

		ModelMapper modelMapper = new ModelMapper();

		List<Cart> userCartList = cartRepository.findAllByUserId(userId);

		Set<Product> selectedProducts = new HashSet<Product>();

		selectedProducts = productsDto.stream().map(productDto -> modelMapper.map(productDto, Product.class))
				.collect(Collectors.toSet());

		if (userCartList.isEmpty()) {
			return newCart(selectedProducts, userId);
		} else {
			Cart userCart = new Cart();
			for (Cart cart : userCartList) {
				if (cart.isPaymentStatus() == false && cart.getUserId().equals(userId)) {
					userCart = cart;
					break;
				}
			}
			return existingCart(selectedProducts, userCart, userId);
		}

	}

	public CartDto existingCart(Set<Product> selectedProductsSet, Cart userCart, String userId)
			throws ServiceException {

		Set<Product> userProducts = new HashSet<Product>();
		userProducts = userCart.getAllSelectedProduct();
		ModelMapper modelMapper = new ModelMapper();
		userCart.setTotalAmount(0);

		for (Product selectedProduct : selectedProductsSet) {
			selectedProduct.setUserId(userId);
			try {
				if (!userCart.getAllSelectedProduct().contains(selectedProduct)) {
				try {
					if(selectedProduct.getQuantity()>0)
					{
					Set<Cart> cartList = new HashSet<Cart>();
					cartList.add(userCart);
					
					selectedProduct.setCart(cartList);
					selectedProduct.setUserId(userId);
					
					userProducts.add(selectedProduct);
					}
					else {
						throw new InvalidProductQuantityException("NEGATIVE_QTY_ADD");
					}
				}
				catch (InvalidProductQuantityException e) {
					throw new ServiceException(e.getMessage());
				}
				} else {
					throw new ProductAlreadyInCartException("ALREADY_IN_CART");
				}
			} catch (Exception e) {
				throw new ServiceException(e.getMessage());
			}
		}
		userCart.setAllSelectedProduct(userProducts);
		for (Product product : userCart.getAllSelectedProduct()) {
			userCart.setTotalAmount(userCart.getTotalAmount() + (product.getQuantity() * product.getProductPrice()));
		}
		return modelMapper.map(cartRepository.saveAndFlush(userCart), CartDto.class);

	}

//	///creating new cart ///
	public CartDto newCart(Set<Product> selectedProducts, String userId) throws ServiceException {

		Cart cart = new Cart();
		Set<Cart> cartList = new HashSet<Cart>();
		cartList.add(cart);
		ModelMapper modelMapper = new ModelMapper();

		cart.setPaymentStatus(false);
		cart.setUserId(userId);
		for (Product product : selectedProducts) {
			try {
				if (product.getQuantity() > 0) {
					product.setUserId(userId);
					product.setCart(cartList);
					cart.setTotalAmount(cart.getTotalAmount() + (product.getProductPrice() * product.getQuantity()));
				}
				else {
					throw new InvalidProductQuantityException("NEGATIVE_QTY_ADD");
				}
			} catch (InvalidProductQuantityException e) {
				throw new  ServiceException(e.getMessage());
			}
		}
		cart.setAllSelectedProduct(selectedProducts);
		return modelMapper.map(cartRepository.save(cart), CartDto.class);

	}

	//////////// removing from cart of a user//////////
	@Override
	@Transactional
	public Set<ProductDto> removeFromCart(Set<ProductDto> productDtoSet, String userId) throws ServiceException {

		Set<Product> selectedProducts = new HashSet<Product>();
		ModelMapper modelMapper = new ModelMapper();
		Set<ProductDto> productsDeleted = new HashSet<ProductDto>();
		selectedProducts = productDtoSet.stream().map(product -> modelMapper.map(product, Product.class))
				.collect(Collectors.toSet());

		List<Cart> userCartList = cartRepository.findAllByUserId(userId);
		try {
			if (userCartList.isEmpty()) {
				throw new NoItemsToDeleteException("NO_ITEMS_TO_DELETE");
			}

			else {
				Cart userCart = new Cart();
				for (Cart cart : userCartList) {
					if (cart.isPaymentStatus() == false && cart.getUserId().equals(userId)) {
						userCart = cart;
						break;
					}

				}
				try {
					Set<Cart> cartSet = new HashSet<Cart>();
					cartSet.add(userCart);
					if (!userCart.getAllSelectedProduct().isEmpty()) {
						Set<Product> oldProducts = userCart.getAllSelectedProduct();
						for (Product userProduct : oldProducts) {
							for (Product selectedProduct : selectedProducts) {
								selectedProduct.setUserId(userId);
								if ((selectedProduct.getProductId() == userProduct.getProductId())
										&& (selectedProduct.getUserId().equals(userId))) {
									productRepository.deleteByUserIdAndProuctId(userProduct.getProductId(), userId);
									selectedProduct.setCart(cartSet);
									productsDeleted.add(modelMapper.map(selectedProduct, ProductDto.class));
								}
							}
						}
					} else {
						throw new NoItemsToDeleteException("NO_ITEMS_TO_DELETE");
					}
				} catch (NoItemsToDeleteException e) {
					throw new ServiceException(e.getMessage());
				}
			}

		} catch (NoItemsToDeleteException e) {
			throw new ServiceException(e.getMessage());
		}

		return productsDeleted;
	}

	@Override
	public CartDto increaseQuantity(Set<ProductDto> productDtoSet, String userId) throws ServiceException {

		Set<Product> selectedProducts = new HashSet<Product>();
		ModelMapper modelMapper = new ModelMapper();
		selectedProducts = productDtoSet.stream().map(product -> modelMapper.map(product, Product.class))
				.collect(Collectors.toSet());

		List<Cart> userCartList = cartRepository.findAllByUserId(userId);
		try {
			if (userCartList.isEmpty()) {
				throw new NoSuchProduct("INVLAID_ITEM");
			}

			else {
				Cart userCart = new Cart();
				for (Cart cart : userCartList) {
					if (cart.isPaymentStatus() == false && cart.getUserId().equals(userId)) {
						userCart = cart;
						break;
					}

				}
				try {
					Set<Cart> cartSet = new HashSet<Cart>();
					cartSet.add(userCart);
					if (!userCart.getAllSelectedProduct().isEmpty()) {
						Set<Product> oldProducts = userCart.getAllSelectedProduct();
						for (Product userProduct : oldProducts) {
							for (Product selectedProduct : selectedProducts) {
								selectedProduct.setUserId(userId);
								if ((selectedProduct.getProductId() == userProduct.getProductId())
										&& (selectedProduct.getUserId().equals(userId))) {
									try {
										if (selectedProduct.getQuantity() > 0) {
											userProduct.setCart(cartSet);
											userProduct.setQuantity(
													userProduct.getQuantity() + selectedProduct.getQuantity());
										} else {
											throw new InvalidProductQuantityException("NEGATIVE_QUANTITY");
										}

									} catch (InvalidProductQuantityException e) {
										throw new ServiceException(e.getMessage());
									}
								}
							}
						}
						userCart.setAllSelectedProduct(oldProducts);
						for (Product product : oldProducts) {
							userCart.setTotalAmount((product.getProductPrice()) * (product.getQuantity()));
						}
					} else {
						throw new NoSuchProduct("INVLAID_ITEM");
					}

					return modelMapper.map(cartRepository.saveAndFlush(userCart), CartDto.class);

				} catch (NoSuchProduct e) {
					throw new ServiceException(e.getMessage());
				}
			}

		} catch (NoSuchProduct e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public CartDto decreaseQuantity(Set<ProductDto> productDtoSet, String userId) throws ServiceException {
		Set<Product> selectedProducts = new HashSet<Product>();
		ModelMapper modelMapper = new ModelMapper();
		selectedProducts = productDtoSet.stream().map(product -> modelMapper.map(product, Product.class))
				.collect(Collectors.toSet());

		List<Cart> userCartList = cartRepository.findAllByUserId(userId);
		try {
			if (userCartList.isEmpty()) {
				throw new NoSuchProduct("INVLAID_ITEM");
			}

			else {
				Cart userCart = new Cart();
				for (Cart cart : userCartList) {
					if (cart.isPaymentStatus() == false && cart.getUserId().equals(userId)) {
						userCart = cart;
						break;
					}

				}
				try {
					Set<Cart> cartSet = new HashSet<Cart>();
					cartSet.add(userCart);
					if (!userCart.getAllSelectedProduct().isEmpty()) {
						Set<Product> oldProducts = userCart.getAllSelectedProduct();
						for (Product userProduct : oldProducts) {
							for (Product selectedProduct : selectedProducts) {
								selectedProduct.setUserId(userId);
								if ((selectedProduct.getProductId() == userProduct.getProductId())
										&& (selectedProduct.getUserId().equals(userId))) {
									try {
										if (selectedProduct.getQuantity() <= userProduct.getQuantity()) {
											try {
												if (selectedProduct.getQuantity() > 0) {
													userProduct.setCart(cartSet);
													userProduct.setQuantity(
															userProduct.getQuantity() - selectedProduct.getQuantity());
												} else {
													throw new InvalidProductQuantityException("NEGATIVE_QUANTITY_DEC");
												}
											} catch (InvalidProductQuantityException e) {
												throw new ServiceException(e.getMessage());
											}
										} else {
											throw new InvalidProductQuantityException("INVALID_QUANTITY");
										}
									} catch (InvalidProductQuantityException e) {
										throw new ServiceException(e.getMessage());
									}
								}
							}
							if (userProduct.getQuantity() == 0) {
								deleteProduct(userProduct.getProductId(), userProduct.getUserId());
							}
						}
						userCart.setAllSelectedProduct(oldProducts);
						for (Product product : oldProducts) {
							userCart.setTotalAmount((product.getProductPrice()) * (product.getQuantity()));
						}
					} else {
						throw new NoSuchProduct("INVLAID_ITEM");
					}

					return modelMapper.map(cartRepository.saveAndFlush(userCart), CartDto.class);

				} catch (NoSuchProduct e) {
					throw new ServiceException(e.getMessage());
				}
			}

		} catch (NoSuchProduct e) {
			throw new ServiceException(e.getMessage());
		}
	}

//delete product
	@Transactional
	private void deleteProduct(int productId, String userId) {
		productRepository.deleteByUserIdAndProuctId(productId, userId);
	}

//////////////empty cart ///////////////////////
	@Override
	@Transactional
	public CartDto emptyMyCart(String userId) throws ServiceException {

		ModelMapper modelMapper = new ModelMapper();

		List<Cart> userCartList = cartRepository.findAllByUserId(userId);

		try {
			if (userCartList.isEmpty()) {
				throw new NoCartExistsException("NO_ITEMS_TO_EMPTY");
			} else {
				Cart userCart = new Cart();
				for (Cart cart : userCartList) {
					if (cart.isPaymentStatus() == false && cart.getUserId().equals(userId)) {
						userCart = cart;
						break;
					}
				}
				try {
					if (userCart != null && userCart.isPaymentStatus() == false
							&& (!userCart.getAllSelectedProduct().isEmpty())) {
						productRepository.deleteByUserId(userId);
						cartRepository.deleteByUserId(userId);
						return modelMapper.map(userCart, CartDto.class);
					} else {
						throw new NoCartExistsException("NO_ITEMS_TO_DELETE");
					}
				} catch (NoCartExistsException e) {
					throw new ServiceException(e.getMessage());
				}
			}
		} catch (NoCartExistsException e) {
			throw new ServiceException(e.getMessage());
		}
	}

//////////// getting cart of a user///////////
	@Override
	public CartDto getCart(String userId) throws ServiceException {
		ModelMapper modelMapper = new ModelMapper();

		List<Cart> userCarts = cartRepository.findAll();
		Cart userCart = new Cart();
		for (Cart cartItr : userCarts) {
			if (cartItr.isPaymentStatus() == false && cartItr.getUserId().equals(userId)) {
				userCart = cartItr;
			}
		}
		try {
			if ((userCart.isPaymentStatus() == false) && (userCart.getUserId() != null)
					&& (userCart.getAllSelectedProduct().isEmpty() == false)) {
				return modelMapper.map(userCart, CartDto.class);
			} else {
				throw new NoItemsToDisplayException("NO_ITEMS_TO_DISPLAY");

			}
		} catch (NoItemsToDisplayException e) {

			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public String changePaymentStatus(int cartId) throws ServiceException {
		try {
			if (cartRepository.existsById(cartId)) {
				Cart cart = cartRepository.findById(cartId).get();
				String userId = cart.getUserId();
				emptyMyCart(userId);
				return "Payment status changed successfully";
			} else {
				throw new NoCartExistsException("INVALID_CART");
			}
		} catch (NoCartExistsException e) {
			throw new ServiceException(e.getMessage());
		}

	}

}