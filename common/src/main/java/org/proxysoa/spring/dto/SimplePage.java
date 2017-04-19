package org.proxysoa.spring.dto;

import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents page info (list of T and totla)
 * Extends PageImpl to have default constructor to let us create it on unnmarshall JSON
 * @author stanislav.lapitsky created 4/14/2017.
 */
public class SimplePage<T> extends PageImpl<T> {
    public SimplePage() {
        super(new ArrayList<>());
    }

    public SimplePage(List<T> content) {
        super(content);
    }
}
