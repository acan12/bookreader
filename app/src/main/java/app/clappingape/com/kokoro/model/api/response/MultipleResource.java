package app.clappingape.com.kokoro.model.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import app.clappingape.com.kokoro.model.Source;

/**
 * Created by arysuryawan on 8/19/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class MultipleResource {

    private String status;
    private String source;
    private String sortBy;
    private List<Source> sources;



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }
}
