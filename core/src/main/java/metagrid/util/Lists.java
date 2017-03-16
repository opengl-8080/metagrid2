package metagrid.util;

import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

public class Lists {
    
    public static <F, T> List<T> to(List<F> from, Function<F, T> converter) {
        if (CollectionUtils.isEmpty(from)) {
            return Collections.emptyList();
        }
        return from.stream().map(converter).collect(toList());
    }
    
    private Lists() {}
}
