package lab.mvc.form.bean;

import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
public class UserFormBean {

    @NotNull//(message="{NotNull.userFormBean.firstName}")
    @Size(min = 2, max = 20)
    String firstName;

    @NotNull
    @Size(min = 2, max = 30)
    String lastName;
}