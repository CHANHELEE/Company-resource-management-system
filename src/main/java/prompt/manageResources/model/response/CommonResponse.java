package prompt.manageResources.model.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class CommonResponse<T> {
    private final Boolean flag;
    private final T result;
}
