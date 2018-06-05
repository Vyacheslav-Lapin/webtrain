package lab.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class SimpleContact implements Contact {
    long id;

    @Default
    String type = "EMAIL";

    String value;
}
