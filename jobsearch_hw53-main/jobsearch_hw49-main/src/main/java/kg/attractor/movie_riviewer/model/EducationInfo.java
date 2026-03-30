package kg.attractor.movie_riviewer.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EducationInfo {
    private Integer id;
    private Integer resumeId;
    private String institution;
    private String program;
    private String startDate;
    private String endDate;
    private String degree;
}