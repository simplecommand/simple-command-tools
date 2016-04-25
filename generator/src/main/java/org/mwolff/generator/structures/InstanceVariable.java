package org.mwolff.generator.structures;

public class InstanceVariable {
    
    private String modifier;
    private String type;
    private String identifier;
    private String cardinality;
    
    public String getModifier() {
        return modifier;
    }
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getIdentifier() {
        return identifier;
    }
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
    public String getCardinality() {
        return cardinality;
    }
    public void setCardinality(String cardinality) {
        this.cardinality = cardinality;
    }

}
