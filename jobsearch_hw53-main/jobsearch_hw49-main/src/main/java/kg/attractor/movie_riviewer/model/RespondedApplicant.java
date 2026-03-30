package kg.attractor.movie_riviewer.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespondedApplicant {
    private Integer id;
    private Integer resumeId;
    private Integer vacancyId;
    private Boolean confirmation;
}