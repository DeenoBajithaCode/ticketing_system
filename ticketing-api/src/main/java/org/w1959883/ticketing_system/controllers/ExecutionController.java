package org.w1959883.ticketing_system.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w1959883.ticketing_system.services.ExecutionService;

@RestController
@RequestMapping( "/api/execution" )
public class ExecutionController
{
    private final ExecutionService executionService;

    public ExecutionController( ExecutionService executionService )
    {
        this.executionService = executionService;
    }

    @PostMapping
    private void setProjectStatus( Boolean executionRequest )
    {
        executionService.setProjectStatus( executionRequest );
    }

}