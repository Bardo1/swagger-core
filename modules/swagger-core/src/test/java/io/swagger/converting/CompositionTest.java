package io.swagger.converting;

import io.swagger.converter.ModelConverters;
import io.swagger.matchers.SerializationMatchers;
import io.swagger.oas.models.composition.AbstractBaseModelWithoutFields;
import io.swagger.oas.models.composition.Animal;
import io.swagger.oas.models.composition.AnimalClass;
import io.swagger.oas.models.composition.Human;
import io.swagger.oas.models.composition.ModelWithFieldWithSubTypes;
import io.swagger.oas.models.media.Schema;
import io.swagger.util.Json;
import io.swagger.util.ResourceUtils;

import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

public class CompositionTest {

    @Test(description = "read a model with required params and description")
    public void readModelWithRequiredParams() throws IOException {
        compareAsJson(Human.class, "Human.json");
    }

    @Test(description = "read a model with composition")
    public void readModelWithComposition() throws IOException {
        compareAsJson(Animal.class, "Animal.json");
    }

    @Test(description = "read a model with composition")
    public void readClassModelWithComposition() throws IOException {
        compareAsJson(AnimalClass.class, "AnimalClass.json");
    }

    @Test(description = "create a model")
    public void createModel() throws IOException {
        compareAsJson(AbstractBaseModelWithoutFields.class, "AbstractBaseModelWithoutFields.json");
    }

    @Test(description = "create a ModelWithFieldWithSubTypes")
    public void createModelWithFieldWithSubTypes() throws IOException {
        compareAsJson(ModelWithFieldWithSubTypes.class, "ModelWithFieldWithSubTypes.json");
    }

    private void compareAsJson(Class<?> cls, String fileName) throws IOException {
        final Map<String, Schema> schemas = ModelConverters.getInstance().readAll(cls);
        Json.prettyPrint(schemas);
        final String json = ResourceUtils.loadClassResource(getClass(), fileName);
        SerializationMatchers.assertEqualsToJson(schemas, json);
    }
}
