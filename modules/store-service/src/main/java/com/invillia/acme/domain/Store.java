package com.invillia.acme.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_stores")
public class Store implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cd_seq_id")
    private Long id;

    @NotEmpty
    @Column(name = "nm_name", length = 100, nullable = false)
    private String name;

    @NotEmpty
    @Column(name = "nm_address", length = 100, nullable = false)
    private String address;

}
