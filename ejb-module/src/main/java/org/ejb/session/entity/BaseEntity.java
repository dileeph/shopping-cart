package org.ejb.session.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.ejb.session.model.core.Indexable;

import java.io.Serializable;

/**
 * This class is the parent class for all entities grouping common data behaviour.
 * <p>
 * 
 *
 */
@SuppressWarnings("serial")
public abstract class BaseEntity implements Serializable, Indexable {

	/**
	 * Utility method overriding 
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
}
