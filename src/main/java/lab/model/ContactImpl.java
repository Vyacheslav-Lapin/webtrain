package lab.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ContactImpl implements Contact {
    long id;
    String value;

    @Default
    String type = "EMAIL";
}
