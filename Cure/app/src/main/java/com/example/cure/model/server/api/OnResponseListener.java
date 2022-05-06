package com.example.cure.model.server.api;

import com.example.cure.model.data.Root;
import com.example.cure.model.data.SpecificRecipeRoot;

public interface OnResponseListener {

    void recipeByIdFetched(SpecificRecipeRoot sr);

    void recipesByQueryFetched(Root r);

}
