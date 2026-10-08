package com.mysite.core.models;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;

/**
 * Sling Model representing an individual team member item within the Team Members component multifield.
 */
@Model(
    adaptables = Resource.class,
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class TeamMemberItem {

    @ValueMapValue
    private String name;

    @ValueMapValue
    private String role;

    @ValueMapValue
    private String photo;

    @ValueMapValue
    private String bio;

    @ValueMapValue
    private String email;

    @ValueMapValue
    private String linkedin;

    private String initials;

    @PostConstruct
    protected void init() {
        if (StringUtils.isNotBlank(name)) {
            String[] parts = name.trim().split("\\s+");
            if (parts.length >= 2) {
                this.initials = ("" + parts[0].charAt(0) + parts[parts.length - 1].charAt(0)).toUpperCase();
            } else if (parts.length == 1 && !parts[0].isEmpty()) {
                this.initials = parts[0].substring(0, Math.min(2, parts[0].length())).toUpperCase();
            } else {
                this.initials = "TM";
            }
        } else {
            this.initials = "TM";
        }
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getPhoto() {
        return photo;
    }

    public String getBio() {
        return bio;
    }

    public String getEmail() {
        return email;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public String getInitials() {
        return initials;
    }

    public boolean hasPhoto() {
        return StringUtils.isNotBlank(photo);
    }
}

