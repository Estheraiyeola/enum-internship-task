package com.enumAfrica.dto.request;

import com.enumAfrica.data.model.ProgramType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;

@Setter
@Getter
public class CreateCohortRequest {
    private String name;
    private String description;
    private String programType;
    private String avatar;
    private String startDate;
    private String endDate;
}
