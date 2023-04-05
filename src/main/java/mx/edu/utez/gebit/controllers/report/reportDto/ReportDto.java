package mx.edu.utez.gebit.controllers.report.reportDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.gebit.models.computer.Computer;
import mx.edu.utez.gebit.models.reason.Reason;
import mx.edu.utez.gebit.models.report.Report;
import mx.edu.utez.gebit.security.entity.User;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ReportDto {
    private Long id;
    private String description;
    private Integer status;
    private Reason reason;
    private Computer computer;
    private User user;

    public Report getReport(){
        return new Report(
                getId(),
                getDescription(),
                getStatus(),
                getReason(),
                getComputer(),
                getUser()
        );
    }

}
