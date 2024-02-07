package prompt.manageResources.model.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

@Getter
@RequiredArgsConstructor
public class SearchResponse<T> {
    private final Page<T> results;
}
