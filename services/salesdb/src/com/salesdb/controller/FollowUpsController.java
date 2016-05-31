/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.salesdb.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/
import com.salesdb.service.FollowUpsService;
import java.io.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.TypeMismatchException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.util.WMMultipartUtils;
import com.wavemaker.runtime.util.WMRuntimeUtils;
import com.wavemaker.runtime.file.model.DownloadResponse;
import com.wordnik.swagger.annotations.*;
import com.salesdb.*;
import com.salesdb.service.*;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;

/**
 * Controller object for domain model class FollowUps.
 * @see com.salesdb.FollowUps
 */
@RestController(value = "Salesdb.FollowUpsController")
@RequestMapping("/salesdb/FollowUps")
@Api(description = "Exposes APIs to work with FollowUps resource.", value = "FollowUpsController")
public class FollowUpsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FollowUpsController.class);

    @Autowired
    @Qualifier("salesdb.FollowUpsService")
    private FollowUpsService followUpsService;

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ApiOperation(value = "Returns the list of FollowUps instances matching the search criteria.")
    public Page<FollowUps> findFollowUpss(Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering FollowUpss list");
        return followUpsService.findAll(queryFilters, pageable);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiOperation(value = "Returns the list of FollowUps instances.")
    public Page<FollowUps> getFollowUpss(Pageable pageable) {
        LOGGER.debug("Rendering FollowUpss list");
        return followUpsService.findAll(pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 * 
	 * @param service
	 */
    protected void setFollowUpsService(FollowUpsService service) {
        this.followUpsService = service;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Creates a new FollowUps instance.")
    public FollowUps createFollowUps(@RequestBody FollowUps instance) {
        LOGGER.debug("Create FollowUps with information: {}", instance);
        instance = followUpsService.create(instance);
        LOGGER.debug("Created FollowUps with information: {}", instance);
        return instance;
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Returns the total count of FollowUps instances.")
    public Long countAllFollowUpss() {
        LOGGER.debug("counting FollowUpss");
        Long count = followUpsService.countAll();
        return count;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Returns the FollowUps instance associated with the given id.")
    public FollowUps getFollowUps(@PathVariable(value = "id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting FollowUps with id: {}", id);
        FollowUps instance = followUpsService.findById(id);
        LOGGER.debug("FollowUps details with id: {}", instance);
        return instance;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Updates the FollowUps instance associated with the given id.")
    public FollowUps editFollowUps(@PathVariable(value = "id") Integer id, @RequestBody FollowUps instance) throws EntityNotFoundException {
        LOGGER.debug("Editing FollowUps with id: {}", instance.getId());
        instance.setId(id);
        instance = followUpsService.update(instance);
        LOGGER.debug("FollowUps details with id: {}", instance);
        return instance;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Deletes the FollowUps instance associated with the given id.")
    public boolean deleteFollowUps(@PathVariable(value = "id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting FollowUps with id: {}", id);
        FollowUps deleted = followUpsService.delete(id);
        return deleted != null;
    }
}
