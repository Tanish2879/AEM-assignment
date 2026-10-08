package com.mysite.core.models;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Sling Model for the Team Members component.
 * Adapts the component Resource and parses the composite multifield children into TeamMemberItem models.
 */
@Model(
    adaptables = {SlingHttpServletRequest.class, Resource.class},
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class TeamMembersModel {

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String subtitle;

    @ValueMapValue
    private String description;

    @ChildResource(name = "members")
    private List<TeamMemberItem> members;

    private List<TeamMemberItem> memberList;

    @PostConstruct
    protected void init() {
        if (members != null) {
            this.memberList = new ArrayList<>(members);
        } else {
            this.memberList = Collections.emptyList();
        }
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getDescription() {
        return description;
    }

    public List<TeamMemberItem> getMembers() {
        return memberList != null ? Collections.unmodifiableList(memberList) : Collections.emptyList();
    }

    public boolean hasMembers() {
        return CollectionUtils.isNotEmpty(memberList);
    }

    public int getMemberCount() {
        return memberList != null ? memberList.size() : 0;
    }

    public boolean hasHeader() {
        return StringUtils.isNotBlank(title) || StringUtils.isNotBlank(description) || StringUtils.isNotBlank(subtitle);
    }
}

