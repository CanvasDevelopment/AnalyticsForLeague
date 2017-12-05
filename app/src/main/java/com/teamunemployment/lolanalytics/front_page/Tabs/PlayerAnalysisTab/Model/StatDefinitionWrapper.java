package com.teamunemployment.lolanalytics.front_page.Tabs.PlayerAnalysisTab.Model;

import java.util.ArrayList;

/**
 * @author Josiah Kendall
 *
 */
public class StatDefinitionWrapper {
    private ArrayList<StatDefinition> definitions;

    public void setDefinitions(ArrayList<StatDefinition> definitions) {
        this.definitions = definitions;
    }

    public ArrayList<StatDefinition> getDefinitions() {
        return definitions;
    }

}
