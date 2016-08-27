package com.edu.cauvery.home;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.value.ValueMap;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Home extends WebPage {

    private static final Logger logger = Logger.getLogger(Home.class.getName());

    Form<ValueMap> form = new Form<ValueMap>("inputForm", new CompoundPropertyModel<ValueMap>(new ValueMap()));
    private CheckBox testCheckBox;
    private Label testCheckBoxLabel;
    private TextField<String> urlText;
    private FeedbackPanel feedback;
    private int counterOne = 0;
    private int counterTwo = 0;
    private Label label;


    public Home() {
        add(new Label("message", "Wicket Controls:"));
        addLink();
        addLinkAjax();
        testCheckBox = new CheckBox("testCheckBox");
        testCheckBoxLabel = new Label("testCheckBoxLabel", "Check Box: ");
        urlText = new TextField<String>("urlText", new Model<String>());
        SubmitButton submitButton = new SubmitButton("submitButton");
        SubmitLink cancelLink = new SubmitLink("cancelLink");

        form.add(testCheckBox);
        form.add(testCheckBoxLabel);
        form.add(urlText);
        form.add(submitButton);
        form.add(cancelLink);
        add(form);

        feedback = new FeedbackPanel("feedback");
        add(feedback);

        final List<Employee> employeeList = new ArrayList<Employee>();
        employeeList.add(new Employee(1, "Test1"));
        employeeList.add(new Employee(2, "Test2"));
        employeeList.add(new Employee(3, "Test3"));


        final ListView<Employee> tables = new ListView<Employee>("tables", employeeList) {

            @Override
            protected void populateItem(ListItem<Employee> item) {

            }
        }
    }


    private class SubmitButton extends Button {
        private static final long serialVersionUID = 105579730351611664L;
        SubmitButton(String name) {
            super(name);
        }

        @Override
        public void onSubmit() {
            logger.log(Level.INFO, urlText.getInput());
            if(urlText.getModelObject() == null) {
                feedback.error("URL can't be null");
            }
        }
    }

    private void addLink() {
        add(new Link("link") {
            @Override
            public void onClick() {
                counterOne++;
            }
        });
        add(new Label("label", new PropertyModel(this, "counterOne")));
    }

    private void addLinkAjax() {
        add(new AjaxFallbackLink("ajaxlink") {
            @Override
            public void onClick(final AjaxRequestTarget target) {
                counterTwo++;
                if (target != null) {
                    target.addComponent(label);
                }
            }
        });
        addLabel();
    }

    private void addLabel() {
        label = new Label("alabel", new PropertyModel(this, "counterTwo"));
        label.setOutputMarkupId(true);
        add(label);
    }

}

