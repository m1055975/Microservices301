package com.mindtree.productservice.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.productservice.dto.FilterParameterDto;
import com.mindtree.productservice.dto.ResponseDto;
import com.mindtree.productservice.exception.ProductException;
import com.mindtree.productservice.exception.service.ServiceException;
import com.mindtree.productservice.service.ModelService;

@RestController
@RequestMapping("/filter")
public class ModelController {
	@Autowired
	private ModelService modelService;

	private static Logger logger = LogManager.getLogger(ModelController.class);

	@GetMapping("/model")
	public ResponseEntity<ResponseDto> filterByModelController(@RequestBody FilterParameterDto filterParameterDto)
			throws ProductException {
		try {
			logger.info("Filtering by modelName");
			return new ResponseEntity<ResponseDto>(
					new ResponseDto(modelService.filterByModelService(filterParameterDto.getModelName())),
					HttpStatus.OK);
		} catch (ServiceException e) {
			throw new ProductException(e.getMessage());
		}
	}

	@GetMapping("/model/price")
	public ResponseEntity<ResponseDto> filterByModelPriceController(@RequestBody FilterParameterDto filterParameterDto)
			throws ProductException {
		try {
			logger.info("Filtering by modelName and price");
			return new ResponseEntity<ResponseDto>(
					new ResponseDto(modelService.filterByModelPriceService(filterParameterDto.getModelName(),
							filterParameterDto.getMinPrice(), filterParameterDto.getMaxPrice())),
					HttpStatus.OK);
		} catch (ServiceException e) {
			throw new ProductException(e.getMessage());
		}
	}

}
