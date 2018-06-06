package lab.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.Value;
import lombok.experimental.NonFinal;

//@Value
@Data
@Builder
@AllArgsConstructor
public class SimpleContact implements Contact {
    long id;
    String value;

    @Default
    String type = "EMAIL";
}
