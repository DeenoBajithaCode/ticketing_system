package org.w1959883.ticketing_system.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w1959883.ticketing_system.exceptions.BadRequestException;
import org.w1959883.ticketing_system.services.ExecutionService;

@RestController
@RequestMapping("/api")
public class ExecutionController {

    private final ExecutionService executionService;

    public ExecutionController(ExecutionService executionService) {
        this.executionService = executionService;
    }

    @PostMapping("/execution")
    public void setProjectStatus(@RequestBody(required = false) Boolean executionRequest) {
        if (executionRequest == null) {
            throw new BadRequestException("Execution request cannot be null");
        }
        executionService.setExecution(executionRequest);
    }
}
