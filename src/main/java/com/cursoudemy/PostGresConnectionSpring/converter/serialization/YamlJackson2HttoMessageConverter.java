package com.cursoudemy.PostGresConnectionSpring.converter.serialization;

import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public class YamlJackson2HttoMessageConverter extends AbstractJackson2HttpMessageConverter {

    public YamlJackson2HttoMessageConverter() {
        super(new YAMLMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL), org.springframework.http.MediaType.parseMediaType("application/x-yaml"));
    }

}
