package com.zs.license.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity //告诉Spring这是一个JPA类
@Table(name = "licenses") //映射到数据库表
/**
 * 包含许可证信息的普通传统Java对象（Plain Old Java Object，POJO）
 */
public class License extends RepresentationModel<License> {
    @Id
    @Column(name = "license_id", nullable = false)//将该字段映射到特定数据库列
    private String licenseId;

    @Column(name = "organization_id", nullable = false)
    private String organizationId;

    @Transient
    private String organizationName ="";

    @Transient
    private String contactName ="";

    @Transient
    private String contactPhone ="";

    @Transient
    private String contactEmail ="";

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "license_type", nullable = false)
    private String licenseType;

    @Column(name = "license_max", nullable = false)
    private Integer licenseMax;

    @Column(name = "license_allocated", nullable = false)
    private Integer licenseAllocated;

    @Column(name = "comment")
    private String comment;

    public License withId(String id)                              { this.setLicenseId(id);                      return this; }
    public License withOrganizationId(String organizationId)      { this.setOrganizationId(organizationId);     return this; }
    public License withProductName(String productName)            { this.setProductName(productName);           return this; }
    public License withLicenseType(String licenseType)            { this.setLicenseType(licenseType);           return this; }
    public License withLicenseMax(Integer licenseMax)             { this.setLicenseMax(licenseMax);             return this; }
    public License withLicenseAllocated(Integer licenseAllocated) { this.setLicenseAllocated(licenseAllocated); return this; }
    public License withComment(String comment)                    { this.setComment(comment);                   return this; }
    public License withOrganizationName(String organizationName)  { this.setOrganizationName(organizationName); return this; }
    public License withContactName(String contactName)            { this.setContactName(contactName);           return this; }
    public License withContactPhone(String contactPhone)          { this.setContactPhone(contactPhone);         return this; }
    public License withContactEmail(String contactEmail)          { this.setContactEmail(contactEmail);         return this; }
}
