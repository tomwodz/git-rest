package pl.tomwodz.gitrest.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class SampleViewResponseDto {
    String respositoryName;
    String owner;
    ArrayList<Branch> branch;
}
