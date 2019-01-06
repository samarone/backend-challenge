package com.invillia.acme.repository.specs;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.invillia.acme.domain.Store;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class StoreSpecification {

	public static Specification<Store> nameEqual(String name) {
		return (root, cq, cb) -> StringUtils.isBlank(name) ? cb.isTrue(cb.literal(Boolean.TRUE))
				: cb.equal(root.get("name"), name);
	}

	public static Specification<Store> addressEqual(String address) {
		return (root, cq, cb) -> StringUtils.isBlank(address) ? cb.isTrue(cb.literal(Boolean.TRUE))
				: cb.equal(root.get("address"), address);
	}

}
