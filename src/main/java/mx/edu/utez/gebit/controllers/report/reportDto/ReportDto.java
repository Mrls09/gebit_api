package mx.edu.utez.gebit.controllers.report.reportDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.gebit.controllers.reason.dtos.ReasonDto;
import mx.edu.utez.gebit.models.bitacora.Bitacora;
import mx.edu.utez.gebit.models.reason.Reason;
import mx.edu.utez.gebit.models.report.Report;
import mx.edu.utez.gebit.security.entity.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ReportDto {
    private Long id;
    private String description;
    private Integer status;
    private Bitacora bitacora;
    private User user;
    private Set<Reason> reasons = new HashSet<>();

    public Report getReport() {
        return new Report(
                getId(),
                getDescription(),
                getStatus(),
                getBitacora(),
                getUser(),
                getReasons()
        );
    }
}

