package com.kata.bankaccount.cucumber;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = {"src/test/resources/deposit.feature", "src/test/resources/withdrawal.feature",
        "src/test/resources"},
    glue = {"classpath:com/kata/bankaccount"}, plugin = {"pretty", "html:target/cucumber-reports"},
    monochrome = true, snippets = SnippetType.CAMELCASE)
public class CucumberTest {

}
