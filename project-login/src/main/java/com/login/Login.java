package com.login;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Login extends Application {

    // Simple variables to track what we're showing
    private Text title;
    private Text subtitle;
    private TextField emailField;
    private PasswordField passwordField;
    private Button userTab;
    private Button barberTab;

    public void start(Stage primaryStage) throws Exception {
        // Create background
        Image backgroundImage = new Image(getClass().getResource("/Assets/Images/barber3.jpeg").toExternalForm());
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setFitWidth(1550);
        backgroundView.setFitHeight(870);
        backgroundView.opacityProperty().set(0.5);
        
        StackPane root = new StackPane();
        root.setPrefSize(1000, 700);
        
        // Create the main login card
        VBox loginCard = createLoginCard();
        
        // Add shadow effect
        DropShadow shadow = new DropShadow();
        shadow.setRadius(20);
        shadow.setOffsetX(5);
        shadow.setOffsetY(10);
        shadow.setColor(Color.rgb(0, 0, 0, 0.9));
        loginCard.setEffect(shadow);

        root.getChildren().addAll(backgroundView, loginCard);

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        primaryStage.setFullScreen(true);
        primaryStage.setTitle("Modern Login");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    private VBox createLoginCard() {
        VBox card = new VBox(25);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(40));
        card.setMaxWidth(700);
        card.setMaxHeight(700);

        // Glass effect style
        card.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0.15);" +
                "-fx-background-radius: 20;" +
                "-fx-border-radius: 20;" +
                "-fx-border-color: rgba(255, 255, 255, 0.3);" +
                "-fx-border-width: 2;"
        );
        card.setEffect(new GaussianBlur(100));

        // 1. CREATE TABS
        HBox tabContainer = createTabs();

        // 2. CREATE LOGO
        ImageView logo = createLogo();

        // 3. CREATE TITLE AND SUBTITLE
        title = new Text("Welcome Back");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        title.setFill(Color.BLACK);

        subtitle = new Text("Please sign in to your account");
        subtitle.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        subtitle.setFill(Color.BLACK);

        // 4. CREATE INPUT FIELDS
        emailField = createStyledTextField("Email");
        passwordField = createStyledPasswordField("Password");

        // 5. CREATE BUTTONS
        Button loginButton = createStyledButton("SIGN IN");
        Button exploreButton = createStyledButton("EXPLORE");
        HBox buttonBox = new HBox(15, loginButton, exploreButton);
        buttonBox.setAlignment(Pos.CENTER);

        // 6. CREATE LINKS
        Hyperlink forgotLink = new Hyperlink("Forgot Password?");
        forgotLink.setTextFill(Color.BLACK);
        forgotLink.setFont(Font.font("Arial", 12));

        HBox signUpSection = new HBox(5);
        signUpSection.setAlignment(Pos.CENTER);
        Text signUpText = new Text("Don't have an account? ");
        signUpText.setFill(Color.BLACK);
        signUpText.setFont(Font.font("Arial", 12));
        Hyperlink signUpLink = new Hyperlink("Sign Up");
        signUpLink.setTextFill(Color.BLACK);
        signUpLink.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        signUpSection.getChildren().addAll(signUpText, signUpLink);

        // PUT EVERYTHING TOGETHER
        card.getChildren().addAll(
                tabContainer,
                logo,
                title,
                subtitle,
                emailField,
                passwordField,
                buttonBox,
                forgotLink,
                signUpSection
        );

        return card;
    }

    // SIMPLE METHOD TO CREATE TABS
    private HBox createTabs() {
        HBox tabContainer = new HBox(15);
        tabContainer.setAlignment(Pos.CENTER);
        tabContainer.setMaxWidth(300);

        // Create USER tab (selected by default)
        userTab = new Button("USER");
        userTab.setPrefHeight(40);
        userTab.setPrefWidth(140);
        userTab.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        makeTabActive(userTab);

        // Create ADMIN tab
        barberTab = new Button("BARBER");
        barberTab.setPrefHeight(40);
        barberTab.setPrefWidth(140);
        barberTab.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        makeTabInactive(barberTab);

        // What happens when you click USER tab
        userTab.setOnAction(e -> {
            makeTabActive(userTab);
            makeTabInactive(barberTab);
            showUserLogin();
        });

        // What happens when you click ADMIN tab
        barberTab.setOnAction(e -> {
            makeTabActive(barberTab);
            makeTabInactive(userTab);
            showBarberLogin();
        });

        tabContainer.getChildren().addAll(userTab, barberTab);
        return tabContainer;
    }

    // SIMPLE METHOD TO MAKE A TAB LOOK ACTIVE
    private void makeTabActive(Button tab) {
        tab.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0.3);" +
                "-fx-background-radius: 10;" +
                "-fx-text-fill: black;" +
                "-fx-border-color: rgba(255, 255, 255, 0.5);" +
                "-fx-border-width: 1;" +
                "-fx-border-radius: 10;" +
                "-fx-cursor: hand;"
        );
    }

    // SIMPLE METHOD TO MAKE A TAB LOOK INACTIVE
    private void makeTabInactive(Button tab) {
        tab.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-background-radius: 10;" +
                "-fx-text-fill: rgba(0, 0, 0, 0.7);" +
                "-fx-cursor: hand;"
        );
    }

    // SIMPLE METHOD TO SHOW USER LOGIN
    private void showUserLogin() {
        title.setText("Welcome Back");
        subtitle.setText("Please sign in to your account");
        emailField.setPromptText("Email");
        passwordField.setPromptText("Password");
    }

    // SIMPLE METHOD TO SHOW ADMIN LOGIN
    private void showBarberLogin() {
        title.setText("Barber Portal");
        subtitle.setText("Barberistrative access required");
        emailField.setPromptText("Barber Email");
        passwordField.setPromptText("Barber Password");
    }

    // CREATE LOGO (same as before)
    private ImageView createLogo() {
        Image loginLogo = new Image(getClass().getResource("/Assets/Images/login_logo.jpeg").toExternalForm());
        ImageView logoView = new ImageView(loginLogo);
        logoView.setFitWidth(100);
        logoView.setFitHeight(100);
        logoView.setPreserveRatio(true);
        logoView.setEffect(new DropShadow());
        logoView.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0.2);" +
                "-fx-background-radius: 90;" +
                "-fx-padding: 10;"
        );
        javafx.scene.shape.Circle clip = new javafx.scene.shape.Circle(50, 50, 50);
        logoView.setClip(clip);
        return logoView;
    }

    // CREATE TEXT FIELD (same as before)
    private TextField createStyledTextField(String placeholder) {
        TextField field = new TextField();
        field.setPromptText(placeholder);
        field.setPrefHeight(45);
        field.setPrefWidth(200);
        field.setMaxWidth(400);
        field.setMinHeight(40);
        field.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0.2);" +
                "-fx-background-radius: 10;" +
                "-fx-border-radius: 10;" +
                "-fx-border-color: rgba(0, 0, 0, 1);" +
                "-fx-border-width: 1;" +
                "-fx-text-fill: black;" +
                "-fx-prompt-text-fill: rgba(0, 0, 0, 1);" +
                "-fx-font-size: 14px;" +
                "-fx-padding: 0 15 0 15;"
        );
        return field;
    }

    // CREATE PASSWORD FIELD (same as before)
    private PasswordField createStyledPasswordField(String placeholder) {
        PasswordField field = new PasswordField();
        field.setPromptText(placeholder);
        field.setPrefHeight(45);
        field.setPrefWidth(200);
        field.setMaxWidth(400);
        field.setMinHeight(40);
        field.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0.2);" +
                "-fx-background-radius: 10;" +
                "-fx-border-radius: 10;" +
                "-fx-border-color: rgba(0, 0, 0, 1);" +
                "-fx-border-width: 1;" +
                "-fx-text-fill: black;" +
                "-fx-prompt-text-fill: rgba(0, 0, 0, 1);" +
                "-fx-font-size: 14px;" +
                "-fx-padding: 0 15 0 15;"
        );
        return field;
    }

    // CREATE BUTTON (same as before)
    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setPrefHeight(40);
        button.setPrefWidth(150);
        button.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        button.setStyle(
                "-fx-background-color: black;" +
                "-fx-background-radius: 25;" +
                "-fx-text-fill: white;" +
                "-fx-border-radius: 25;" +
                "-fx-cursor: hand;"
        );

        // Hover effects
        button.setOnMouseEntered(e -> {
            button.setStyle(
                    "-fx-background-color: darkgrey;" +
                    "-fx-background-radius: 25;" +
                    "-fx-text-fill: white;" +
                    "-fx-border-radius: 25;" +
                    "-fx-cursor: hand;"
            );
        });

        button.setOnMouseExited(e -> {
            button.setStyle(
                    "-fx-background-color: black;" +
                    "-fx-background-radius: 25;" +
                    "-fx-text-fill: white;" +
                    "-fx-border-radius: 25;" +
                    "-fx-cursor: hand;"
            );
        });

        // Click action
        button.setOnAction(e -> {
            System.out.println(text + " button clicked!");
        });

        return button;
    }
}