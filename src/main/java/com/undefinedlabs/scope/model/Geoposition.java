package com.undefinedlabs.scope.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Geoposition {

    public static final Geoposition EMPTY = new Geoposition("0", "0");

    private final String latitude;
    private final String longitude;

    @JsonCreator
    public Geoposition(@JsonProperty("lat") final String latitude, @JsonProperty("lon") final String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @JsonGetter
    public String getLatitude() {
        return latitude;
    }

    @JsonGetter
    public String getLongitude() {
        return longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Geoposition that = (Geoposition) o;

        return new EqualsBuilder()
                .append(latitude, that.latitude)
                .append(longitude, that.longitude)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(latitude)
                .append(longitude)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("latitude", latitude)
                .append("longitude", longitude)
                .toString();
    }
}
