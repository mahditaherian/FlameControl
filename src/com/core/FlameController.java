package com.core;

import com.core.processor.Criteria;
import java.security.Provider;

/**
 *
 * @author Mahdi
 */
public class FlameController {

    private Criteria criteria;
    private Provider provider;

    public FlameController(Provider provider, Criteria criteria) {
        this.criteria = criteria;
        this.provider = provider;
    }

}
