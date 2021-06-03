package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main extends Application {

    Scene sceneHome, sceneMortgage, sceneFinance, sceneLoan, sceneHelp, sceneHistory;
    String output;


    @Override
    public void start(Stage primaryStage) throws MalformedURLException, FileNotFoundException {

        DecimalFormat df = new DecimalFormat("###,###,##0.00");

        File file = new File("data.txt");
        PrintWriter out = new PrintWriter(file);

        Image imageBackground = new Image(String.valueOf(this.getClass().getResource("/images/bg.png")));
        Image imageHistory = new Image(String.valueOf(this.getClass().getResource("/images/history.png")));
        final ImageView background = new ImageView();
        final ImageView historyIcon = new ImageView();

        background.setImage(imageBackground);

        historyIcon.setImage(imageHistory);
        historyIcon.setLayoutX(590);
        historyIcon.setLayoutY(400);

        String style = "-fx-min-width:200; -fx-min-height:50; -fx-background-radius:10; -fx-background-color:#dbe2ef; -fx-font-size:15";
        String styleHover = "-fx-min-width:200; -fx-min-height:50; -fx-background-radius:10; -fx-background-color:#3f72af; -fx-text-fill:white; -fx-font-size:15";



        //---------------------------------------------------------------------------Home Screen---------------------------------------------------------------------------------------------------------------------------



        Pane windowMain = new Pane();

        Label lblTitle = new Label("Financial Calculator");
        lblTitle.setLayoutX(300);
        lblTitle.setLayoutY(25);
        lblTitle.setStyle("-fx-min-width:200; -fx-min-height:50; -fx-alignment:center; -fx-font: normal bold 20px 'serif';");

        Button btnMortCal = new Button("Mortgage Calculator");
        btnMortCal.setLayoutX(300);
        btnMortCal.setLayoutY(125);
        btnMortCal.setStyle(style);
        btnMortCal.setOnMouseEntered(event -> btnMortCal.setStyle(styleHover));
        btnMortCal.setOnMouseExited(event -> btnMortCal.setStyle(style));

        Button btnLoanCal = new Button("Loan Calculator");
        btnLoanCal.setLayoutX(300);
        btnLoanCal.setLayoutY(225);
        btnLoanCal.setStyle(style);
        btnLoanCal.setOnMouseEntered(event -> btnLoanCal.setStyle(styleHover));
        btnLoanCal.setOnMouseExited(event -> btnLoanCal.setStyle(style));

        Button btnFinanceCal = new Button("Finance Calculator");
        btnFinanceCal.setLayoutX(300);
        btnFinanceCal.setLayoutY(325);
        btnFinanceCal.setStyle(style);
        btnFinanceCal.setOnMouseEntered(event -> btnFinanceCal.setStyle(styleHover));
        btnFinanceCal.setOnMouseExited(event -> btnFinanceCal.setStyle(style));

        Button btnHelp = new Button("?");
        btnHelp.setLayoutX(650);
        btnHelp.setLayoutY(400);
        btnHelp.setPrefSize(50, 50);
        btnHelp.setStyle("-fx-background-radius:100; -fx-background-color:#dbe2ef; -fx-font-size:20; -fx-font-weight:bold");
        btnHelp.setOnMouseEntered(event -> btnHelp.setStyle("-fx-background-radius:100; -fx-background-color:#3f72af; -fx-font-size:20;" +
                "-fx-text-fill:white; -fx-font-weight:bold"));
        btnHelp.setOnMouseExited(event -> btnHelp.setStyle("-fx-background-radius:100; -fx-background-color:#dbe2ef; -fx-font-size:20; -fx-font-weight:bold"));

        Button btnHistory = new Button();
        btnHistory.setLayoutX(590);
        btnHistory.setLayoutY(400);
        btnHistory.setPrefSize(50, 50);
        btnHistory.setStyle("-fx-background-radius:100; -fx-background-color:#dbe2ef; -fx-font-size:20;");
        btnHistory.setOnMouseEntered(event -> btnHistory.setStyle("-fx-background-radius:100; -fx-background-color:#3f72af; -fx-font-size:20; -fx-text-fill:white"));
        btnHistory.setOnMouseExited(event -> btnHistory.setStyle("-fx-background-radius:100; -fx-background-color:#dbe2ef; -fx-font-size:20"));
        historyIcon.setOnMouseEntered(event -> btnHistory.setStyle("-fx-background-radius:100; -fx-background-color:#3f72af; -fx-font-size:20; -fx-text-fill:white"));
        historyIcon.setOnMouseExited(event -> btnHistory.setStyle("-fx-background-radius:100; -fx-background-color:#dbe2ef; -fx-font-size:20"));

        windowMain.getChildren().addAll(background, lblTitle, btnMortCal, btnLoanCal, btnFinanceCal, btnHelp, btnHistory, historyIcon);

        btnMortCal.setOnAction(e -> changeSceneAndName(primaryStage, sceneMortgage,"Mortgage Calculator"));
        btnFinanceCal.setOnAction(e -> changeSceneAndName(primaryStage, sceneFinance, "Finance Calculator"));
        btnLoanCal.setOnAction(e -> changeSceneAndName(primaryStage, sceneLoan, "Loan Calculator"));
        btnHelp.setOnAction(event -> changeSceneAndName(primaryStage, sceneHelp, "Help Screen"));
        btnHistory.setOnAction(event -> changeSceneAndName(primaryStage, sceneHistory, "History"));
        historyIcon.setOnMouseClicked(event -> changeSceneAndName(primaryStage, sceneHistory, "History"));


        primaryStage.setTitle("Home Page");

        sceneHome = new Scene(windowMain, 800, 500);

        primaryStage.setScene(sceneHome);
        primaryStage.show();


        //------------------------------------------------------------Mortgage Calculator-------------------------------------------------------------------------------------------------


        int x = 150;
        int y = 150;

        Label lblTitleMort = new Label("Modify the values and click the Calculate button to use");
        lblTitleMort.setLayoutX(0);
        lblTitleMort.setLayoutY(10);
        lblTitleMort.setStyle("-fx-background-color: lightBlue; -fx-min-width:1000; -fx-alignment: center; -fx-font-size:16;");

        Label lblHomePrice = new Label("Home Price");
        lblHomePrice.setLayoutX(x);
        lblHomePrice.setLayoutY(y);

        TextField txtHomePrice = new TextField();
        txtHomePrice.setLayoutX(x + 100);
        txtHomePrice.setLayoutY(y);
        txtHomePrice.setPromptText("$");

        Label lblDownPayment = new Label("Down Payment");
        lblDownPayment.setLayoutX(x);
        lblDownPayment.setLayoutY(y + 50);

        TextField txtDownPayment = new TextField();
        txtDownPayment.setLayoutX(x + 100);
        txtDownPayment.setLayoutY(y + 50);
        txtDownPayment.setPromptText("$");

        ComboBox cb1 = new ComboBox();
        cb1.getItems().addAll("$", "%");
        cb1.setLayoutX(x + 255);
        cb1.setLayoutY(y + 50);

        Label lblLoanTerm = new Label("Loan Term");
        lblLoanTerm.setLayoutX(x);
        lblLoanTerm.setLayoutY(y + 100);

        TextField txtLoanTerm = new TextField();
        txtLoanTerm.setLayoutX(x + 100);
        txtLoanTerm.setLayoutY(y + 100);
        txtLoanTerm.setPromptText("Years");

        Label lblMortIntRate = new Label("Interest Rate");
        lblMortIntRate.setLayoutX(x);
        lblMortIntRate.setLayoutY(y + 150);

        TextField txtIntRate = new TextField();
        txtIntRate.setLayoutX(x + 100);
        txtIntRate.setLayoutY(y + 150);
        txtIntRate.setPromptText("$");

        Label lblMortMonthlyPay = new Label("Monthly Pay");
        lblMortMonthlyPay.setLayoutX(x);
        lblMortMonthlyPay.setLayoutY(y + 200);

        TextField txtMortMonthlyPay = new TextField();
        txtMortMonthlyPay.setLayoutX(x + 100);
        txtMortMonthlyPay.setLayoutY(y + 200);
        txtMortMonthlyPay.setPromptText("$");

        Button btnMortgageCalculate = new Button("Calculate");
        btnMortgageCalculate.setLayoutX(250);
        btnMortgageCalculate.setLayoutY(450);
        btnMortgageCalculate.setStyle("-fx-background-color: lightBlue; -fx-background-radius:10; -fx-font-size:20;");
        btnMortgageCalculate.setOnMouseEntered(event -> btnMortgageCalculate.setStyle("-fx-background-radius:10; -fx-font-size:20;" +
                " -fx-background-color:#3f72af; -fx-text-fill:white"));
        btnMortgageCalculate.setOnMouseExited(event -> btnMortgageCalculate.setStyle("-fx-font-size:20; -fx-background-radius:10; " +
                "-fx-background-color:lightBlue"));

        Label lblMonthlyPay = new Label(" Monthly Pay : ");
        lblMonthlyPay.setLayoutX(550);
        lblMonthlyPay.setLayoutY(100);
        lblMonthlyPay.setStyle("-fx-background-color: LightGreen; -fx-min-width:400; -fx-font-size:20;");

        // CSS Styling

        String styleLbl = "-fx-background-color:lightGrey; -fx-min-width:200; -fx-font-size : 13; -fx-padding:3";
        String styleVal = "-fx-background-color:lightGrey; -fx-min-width:100; -fx-font: normal bold 15px 'serif'; -fx-padding:3";

        Label lblHousePrice = new Label("House Price");
        lblHousePrice.setLayoutX(550);
        lblHousePrice.setLayoutY(150);
        lblHousePrice.setStyle(styleLbl);

        Label lblHousePriceVal = new Label(" ");
        lblHousePriceVal.setLayoutX(850);
        lblHousePriceVal.setLayoutY(150);
        lblHousePriceVal.setStyle(styleVal);

        Label lblLoanAmount = new Label("Loan Amount");
        lblLoanAmount.setLayoutX(550);
        lblLoanAmount.setLayoutY(200);
        lblLoanAmount.setStyle(styleLbl);

        Label lblLoanAmountVal = new Label(" ");
        lblLoanAmountVal.setLayoutX(850);
        lblLoanAmountVal.setLayoutY(200);
        lblLoanAmountVal.setStyle(styleVal);

        Label lblDwnPayment = new Label("Down Payment");
        lblDwnPayment.setLayoutX(550);
        lblDwnPayment.setLayoutY(250);
        lblDwnPayment.setStyle(styleLbl);

        Label lblDwnPaymentVal = new Label(" ");
        lblDwnPaymentVal.setLayoutX(850);
        lblDwnPaymentVal.setLayoutY(250);
        lblDwnPaymentVal.setStyle(styleVal);

        Label lblTotN = new Label("Total of _ Mortgage Payments");
        lblTotN.setLayoutX(550);
        lblTotN.setLayoutY(300);
        lblTotN.setStyle(styleLbl);

        Label lblTotNVal = new Label(" ");
        lblTotNVal.setLayoutX(850);
        lblTotNVal.setLayoutY(300);
        lblTotNVal.setStyle(styleVal);

        Label lblMortTotInt = new Label("Total Interest");
        lblMortTotInt.setLayoutX(550);
        lblMortTotInt.setLayoutY(350);
        lblMortTotInt.setStyle(styleLbl);

        Label lblTotIntVal = new Label(" ");
        lblTotIntVal.setLayoutX(850);
        lblTotIntVal.setLayoutY(350);
        lblTotIntVal.setStyle(styleVal);

        Button btnMortBack = new Button("Back");
        btnMortBack.setLayoutX(800);
        btnMortBack.setLayoutY(620);
        btnMortBack.setStyle("-fx-background-color: lightblue; -fx-background-radius:10; -fx-font-size:20;");
        btnMortBack.setOnMouseEntered(event -> btnMortBack.setStyle("-fx-background-radius:10; -fx-font-size:20;" +
                " -fx-background-color:#3f72af; -fx-text-fill:white"));
        btnMortBack.setOnMouseExited(event -> btnMortBack.setStyle("-fx-font-size:20; -fx-background-radius:10; " +
                "-fx-background-color:lightBlue"));
        btnMortBack.setOnAction(e -> changeSceneAndName(primaryStage, sceneHome, "Home Page"));

        Pane windowMortgage = new Pane();
        windowMortgage.getChildren().addAll(lblTitleMort, lblHomePrice, txtHomePrice, lblDownPayment, txtDownPayment, lblLoanTerm, txtLoanTerm, lblMortIntRate,
                txtIntRate, lblMortMonthlyPay, txtMortMonthlyPay, cb1,
                btnMortgageCalculate, lblMonthlyPay, lblHousePrice, lblHousePriceVal, lblLoanAmount,
                lblLoanAmountVal, lblDwnPayment, lblDwnPaymentVal, lblTotN, lblTotNVal, lblMortTotInt, lblTotIntVal, btnMortBack);

        keyboard(txtHomePrice, windowMortgage);
        keyboard(txtDownPayment, windowMortgage);
        keyboard(txtLoanTerm, windowMortgage);
        keyboard(txtIntRate, windowMortgage);
        keyboard(txtMortMonthlyPay, windowMortgage);

        btnMortgageCalculate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                double homePrice;
                double downPayment;
                double loanTermInMonths;
                double interestRate;
                double monthlyPay;

                if (txtHomePrice.getText().equals("")){
                    downPayment = Double.parseDouble(txtDownPayment.getText());
                    loanTermInMonths = Double.parseDouble(txtLoanTerm.getText()) * 12;
                    interestRate = Double.parseDouble(txtIntRate.getText()) * 0.01 / 12;
                    monthlyPay = Double.parseDouble(txtMortMonthlyPay.getText());


                    homePrice = downPayment + ((12 * monthlyPay * (Math.pow((1 + (interestRate / 12)), (12 * loanTermInMonths)) - 1)) / (interestRate * Math.pow((1 + (interestRate / 12)), (12 * loanTermInMonths))));
                    txtHomePrice.setText(df.format(homePrice));
                }

                else if (txtDownPayment.getText().equals("")){
                    homePrice = Double.parseDouble(txtHomePrice.getText());
                    loanTermInMonths = Double.parseDouble(txtLoanTerm.getText()) * 12;
                    interestRate = Double.parseDouble(txtIntRate.getText()) * 0.01 / 12;
                    monthlyPay = Double.parseDouble(txtMortMonthlyPay.getText());

                    downPayment = homePrice - ((monthlyPay * (Math.pow((1 + interestRate), loanTermInMonths) - 1))/ interestRate * (Math.pow((1 + interestRate), loanTermInMonths)));
                    txtDownPayment.setText(df.format(downPayment));
                }

                else if (txtLoanTerm.getText().equals("")){
                    homePrice = Double.parseDouble(txtHomePrice.getText());
                    if (cb1.getValue().equals("%")) {
                        downPayment = Double.parseDouble(txtHomePrice.getText()) * Double.parseDouble(txtDownPayment.getText()) * 0.01;
                    } else {
                        downPayment = Double.parseDouble(txtDownPayment.getText());
                    }

                    interestRate = Double.parseDouble(txtIntRate.getText()) * 0.01 / 12;
                    monthlyPay = Double.parseDouble(txtMortMonthlyPay.getText());
                    loanTermInMonths = (Math.log((monthlyPay / (monthlyPay - ((interestRate/12) * (homePrice - downPayment)))))) /  (12 * Math.log(1 + (interestRate/12)));
                    txtLoanTerm.setText(df.format(loanTermInMonths));
                }

                else if (txtMortMonthlyPay.getText().equals("")) {

                    homePrice = Double.parseDouble(txtHomePrice.getText());
                    loanTermInMonths = Double.parseDouble(txtLoanTerm.getText()) * 12;

                    lblTotN.setText("Total of " + loanTermInMonths + " Mortgage Payments");

                    interestRate = Double.parseDouble(txtIntRate.getText()) * 0.01 / 12;

                    if (cb1.getValue().equals("%")) {
                        downPayment = Double.parseDouble(txtHomePrice.getText()) * Double.parseDouble(txtDownPayment.getText()) * 0.01;
                    } else {
                        downPayment = Double.parseDouble(txtDownPayment.getText());
                    }

                    double P = homePrice - downPayment;
                    double A = (P * interestRate * Math.pow((1 + interestRate), loanTermInMonths)) / (Math.pow((1 + interestRate), loanTermInMonths) - 1);

                    lblMonthlyPay.setText(" Monthly Pay :          $ " + df.format(A));

                    txtMortMonthlyPay.setText(df.format(A));
                    lblHousePriceVal.setText("$ " + df.format(homePrice));
                    lblLoanAmountVal.setText("$ " + df.format(P));
                    lblDwnPaymentVal.setText("$ " + df.format(downPayment));
                    lblTotNVal.setText("$ " + df.format(A * loanTermInMonths));
                    lblTotIntVal.setText("$ " + df.format(A * loanTermInMonths - P));


                    output = "------------------Mortgage Calculation------------------ \n" +
                            "Home Price : " + df.format(homePrice) + "\n" +
                            "Down Payment : " + df.format(downPayment) + "\n" +
                            "Loan Term : " + df.format(loanTermInMonths) + "\n" +
                            "Interest Rate : " + df.format(interestRate) + "\n" +
                            "Monthly Pay : " + df.format(A) + "\n"
                            + "--------------------------------------------------------\n";

                    out.write(output + "\n");
                    out.flush();
                }

            }
        });


        sceneMortgage = new Scene(windowMortgage, 1000, 800);


        //------------------------------------------------------------Finance Calculator--------------------------------------------------------------------------------------------


        Label lblFinTitle = new Label("Modify the values and click the Calculate button to use");
        lblFinTitle.setLayoutX(0);
        lblFinTitle.setLayoutY(10);
        lblFinTitle.setStyle("-fx-background-color: lightBlue; -fx-min-width:1000; -fx-alignment: center; -fx-font-size:16;");

        Label lblSavingsType = new Label("Savings Type");
        lblSavingsType.setLayoutX(50);
        lblSavingsType.setLayoutY(75);

        ComboBox cbSavingsType = new ComboBox();
        cbSavingsType.getItems().addAll("Simple Savings", "Compound Savings");
        cbSavingsType.setLayoutX(200);
        cbSavingsType.setLayoutY(75);

        Label lblStartPrincipal = new Label("Principal Amount");
        lblStartPrincipal.setLayoutX(50);
        lblStartPrincipal.setLayoutY(125);

        TextField txtStartPrincipal = new TextField();
        txtStartPrincipal.setLayoutX(200);
        txtStartPrincipal.setLayoutY(125);
        txtStartPrincipal.setPromptText("$");

        Label lblNoPrds = new Label("Period");
        lblNoPrds.setLayoutX(50);
        lblNoPrds.setLayoutY(175);

        TextField txtNoPrds = new TextField();
        txtNoPrds.setLayoutX(200);
        txtNoPrds.setLayoutY(175);
        txtNoPrds.setPromptText("Years");

        Label lblFinanceInterestRate = new Label("Interest Rate");
        lblFinanceInterestRate.setLayoutX(50);
        lblFinanceInterestRate.setLayoutY(225);

        TextField txtFinanceInterestRate = new TextField();
        txtFinanceInterestRate.setLayoutX(200);
        txtFinanceInterestRate.setLayoutY(225);
        txtFinanceInterestRate.setPromptText("%");

        Label lblPmt = new Label("PMT(Annuity Payment)");
        lblPmt.setLayoutX(50);
        lblPmt.setLayoutY(275);

        TextField txtPmt = new TextField();
        txtPmt.setLayoutX(200);
        txtPmt.setLayoutY(275);
        txtPmt.setPromptText("$");

        Label lblFV = new Label("FV (Future Value)");
        lblFV.setLayoutX(50);
        lblFV.setLayoutY(325);

        TextField txtFV = new TextField();
        txtFV.setLayoutX(200);
        txtFV.setLayoutY(325);
        txtFV.setPromptText("$");

        Button btnFinanceCalculate = new Button("Calculate");
        btnFinanceCalculate.setLayoutX(150);
        btnFinanceCalculate.setLayoutY(400);
        btnFinanceCalculate.setStyle("-fx-background-color: lightBlue; -fx-background-radius:10; -fx-font-size:20;");
        btnFinanceCalculate.setOnMouseEntered(event -> btnFinanceCalculate.setStyle("-fx-background-radius:10; -fx-font-size:20;" +
                " -fx-background-color:#3f72af; -fx-text-fill:white"));
        btnFinanceCalculate.setOnMouseExited(event -> btnFinanceCalculate.setStyle("-fx-font-size:20; -fx-background-radius:10; " +
                "-fx-background-color:lightBlue"));

        Label lblResults = new Label(" Results");
        lblResults.setLayoutX(500);
        lblResults.setLayoutY(50);
        lblResults.setStyle("-fx-background-color: lightGreen; -fx-min-width:380; -fx-font-size:20;");

        Label lblFinanceFV = new Label("FV (Future Value)");
        lblFinanceFV.setLayoutX(500);
        lblFinanceFV.setLayoutY(100);
        lblFinanceFV.setStyle(styleLbl);

        Label lblFinanceFVVal = new Label();
        lblFinanceFVVal.setLayoutX(780);
        lblFinanceFVVal.setLayoutY(100);
        lblFinanceFVVal.setStyle(styleVal);

        Label lblPV = new Label("PV (Present Value)");
        lblPV.setLayoutX(500);
        lblPV.setLayoutY(140);
        lblPV.setStyle(styleLbl);

        Label lblPVVal = new Label();
        lblPVVal.setLayoutX(780);
        lblPVVal.setLayoutY(140);
        lblPVVal.setStyle(styleVal);

        Label lblNPeriods = new Label("N (Number of Periods)");
        lblNPeriods.setLayoutX(500);
        lblNPeriods.setLayoutY(180);
        lblNPeriods.setStyle(styleLbl);

        Label lblNPeriodsVal = new Label();
        lblNPeriodsVal.setLayoutX(780);
        lblNPeriodsVal.setLayoutY(180);
        lblNPeriodsVal.setStyle(styleVal);

        Label lblFinIntRate = new Label("I/Y (Interest Rate)");
        lblFinIntRate.setLayoutX(500);
        lblFinIntRate.setLayoutY(220);
        lblFinIntRate.setStyle(styleLbl);

        Label lblFinIntRateVal = new Label();
        lblFinIntRateVal.setLayoutX(780);
        lblFinIntRateVal.setLayoutY(220);
        lblFinIntRateVal.setStyle(styleVal);

        Label lblPmtPay = new Label("PMT (Periodic Payment)");
        lblPmtPay.setLayoutX(500);
        lblPmtPay.setLayoutY(260);
        lblPmtPay.setStyle(styleLbl);

        Label lblPmtPayVal = new Label();
        lblPmtPayVal.setLayoutX(780);
        lblPmtPayVal.setLayoutY(260);
        lblPmtPayVal.setStyle(styleVal);

        Label lblStrtInv = new Label("Starting Investment");
        lblStrtInv.setLayoutX(500);
        lblStrtInv.setLayoutY(300);
        lblStrtInv.setStyle(styleLbl);

        Label lblStrtInvVal = new Label();
        lblStrtInvVal.setLayoutX(780);
        lblStrtInvVal.setLayoutY(300);
        lblStrtInvVal.setStyle(styleVal);

        Label lblTotPrncpl = new Label("Total Principal");
        lblTotPrncpl.setLayoutX(500);
        lblTotPrncpl.setLayoutY(340);
        lblTotPrncpl.setStyle(styleLbl);

        Label lblTotPrncplVal = new Label();
        lblTotPrncplVal.setLayoutX(780);
        lblTotPrncplVal.setLayoutY(340);
        lblTotPrncplVal.setStyle(styleVal);

        Label lblFinTotInt = new Label("Total Interest");
        lblFinTotInt.setLayoutX(500);
        lblFinTotInt.setLayoutY(380);
        lblFinTotInt.setStyle(styleLbl);

        Label lblFinTotIntVal = new Label();
        lblFinTotIntVal.setLayoutX(780);
        lblFinTotIntVal.setLayoutY(380);
        lblFinTotIntVal.setStyle(styleVal);

        Button btnFinBack = new Button("Back");
        btnFinBack.setLayoutX(800);
        btnFinBack.setLayoutY(620);
        btnFinBack.setStyle("-fx-background-color: lightBlue; -fx-background-radius:10; -fx-font-size:20;");
        btnFinBack.setOnMouseEntered(event -> btnFinBack.setStyle("-fx-background-radius:10; -fx-font-size:20;" +
                " -fx-background-color:#3f72af; -fx-text-fill:white"));
        btnFinBack.setOnMouseExited(event -> btnFinBack.setStyle("-fx-font-size:20; -fx-background-radius:10; " +
                "-fx-background-color:lightBlue"));
        btnFinBack.setOnAction(e -> changeSceneAndName(primaryStage, sceneHome, "Home Page"));

        Pane windowFinance = new Pane();
        windowFinance.getChildren().addAll(lblFinTitle, lblSavingsType, cbSavingsType, lblStartPrincipal, txtStartPrincipal, lblNoPrds, txtNoPrds,
                lblFinanceInterestRate, txtFinanceInterestRate, lblPmt, txtPmt, lblFV, txtFV, btnFinanceCalculate, lblResults, lblFinanceFV, lblFinanceFVVal,
                lblPV, lblPVVal, lblNPeriods, lblNPeriodsVal, lblFinIntRate, lblFinIntRateVal, lblPmtPay, lblPmtPayVal, lblStrtInv, lblStrtInvVal, lblTotPrncpl,
                lblTotPrncplVal, lblFinTotInt, lblFinTotIntVal, btnFinBack);

        keyboard(txtNoPrds, windowFinance);
        keyboard(txtStartPrincipal, windowFinance);
        keyboard(txtFinanceInterestRate, windowFinance);
        keyboard(txtPmt, windowFinance);
        keyboard(txtFV, windowFinance);

        btnFinanceCalculate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


                double t;
                double PV;
                double r;
                double FV;
                double PMT;
                int n = 12;

                try {
                    t = Double.parseDouble(txtNoPrds.getText()) ;
                } catch (Exception e){
                    t = 0;
                }

                try {
                    PV = Double.parseDouble(txtStartPrincipal.getText());
                } catch (Exception e){
                    PV = 0;
                }

                try {
                    r = Double.parseDouble(txtFinanceInterestRate.getText()) * 0.01;
                } catch (Exception e){
                    r = 0;
                }

                try {
                    FV = Double.parseDouble(txtFV.getText());
                } catch (Exception e){
                    FV = 0;
                }

                try {
                    PMT = Double.parseDouble(txtPmt.getText());
                } catch (Exception e){
                    PMT = 0;
                }


                double powerNt = Math.pow((1 + ( r / n)), (12 * t));


                double financeSimpleInterest;
                if (cbSavingsType.getValue().equals("Simple Savings")) {
                    financeSimpleInterest = (PV * r * t) + PV;
                    txtFV.setText("$ " + df.format(financeSimpleInterest));

                    lblFinanceFVVal.setText("$ " + df.format(financeSimpleInterest));
                    lblPVVal.setText("$ " + df.format(PV));
                    lblNPeriodsVal.setText(String.valueOf(t));
                    lblFinIntRateVal.setText(String.valueOf(r));
                    lblFinTotIntVal.setText(String.valueOf("$ " + df.format(financeSimpleInterest - PV)));
                }


                else if (cbSavingsType.getValue().equals("Compound Savings") && txtStartPrincipal.getText().equals("")) {

                    PV = (FV - (PMT * ((Math.pow((1 + (r/n)), n * t) - 1) / (r/n))))/ (Math.pow((1 + (r/n)), n * t));
                    txtStartPrincipal.setText("$ " + df.format(PV));
                }


                else if (cbSavingsType.getValue().equals("Compound Savings") && txtNoPrds.getText().equals("")) {

                    t = Math.log(((((r * FV) / n) + PMT) / (((PV * r) / n) + PMT))) / (n * Math.log(1 + (r/n)));
                    txtNoPrds.setText(String.valueOf(df.format(t)));
                }


                else if (cbSavingsType.getValue().equals("Compound Savings") && txtFV.getText().equals("")) {

                    FV = (PV * powerNt) + (PMT * ((powerNt - 1) / (12 * t)));

                    txtFV.setText("$ " + df.format(FV));
                }


                else if (cbSavingsType.getValue().equals("Compound Savings") && txtPmt.getText().equals("")) {

                    /*PMT = (PV + ((PV + FV) / (Math.pow((1 + r), t) - 1))) * r / (1 + r);*/

                    PMT = (FV - (PV * Math.pow((1 + (r / n)), (n * t)))) / ((Math.pow((1 + (r / n)), (n * t)) - 1) / (r / n));

                    txtPmt.setText("$ " + df.format(PMT));
                }

                output = "------------------Savings Calculation------------------ \n" +
                        "Savings Type : " + cbSavingsType.getValue() + "\n" +
                        "Principal Amount : " + txtStartPrincipal.getText() + "\n" +
                        "Period : " + txtNoPrds.getText() + "years \n" +
                        "Interest Rate : " + txtFinanceInterestRate.getText() + "\n" +
                        "Monthly Payment : " + txtPmt.getText() + "\n" +
                        "Future Value : " + txtFV.getText() + "\n" +
                        "--------------------------------------------------------\n";

                out.write(output + "\n");
                out.flush();
            }
        });

        sceneFinance = new Scene(windowFinance, 1000, 800);


        //------------------------------------------------------Loan Calculator-----------------------------------------------------------------------------------------------------


        Label lblLnTitle = new Label("Modify the values and click the Calculate button to use");
        lblLnTitle.setLayoutX(0);
        lblLnTitle.setLayoutY(10);
        lblLnTitle.setStyle("-fx-background-color: lightBlue; -fx-min-width:1000; -fx-alignment: center; -fx-font-size:16;");

        Label lblAutoPrice = new Label("Auto Price");
        lblAutoPrice.setLayoutX(50);
        lblAutoPrice.setLayoutY(50);

        TextField txtAutoPrice = new TextField();
        txtAutoPrice.setLayoutX(210);
        txtAutoPrice.setLayoutY(50);
        txtAutoPrice.setPromptText("$");

        Label lblLnLoanTerm = new Label("Loan Term");
        lblLnLoanTerm.setLayoutX(50);
        lblLnLoanTerm.setLayoutY(100);

        TextField txtLnLoanTerm = new TextField();
        txtLnLoanTerm.setLayoutX(210);
        txtLnLoanTerm.setLayoutY(100);
        txtLnLoanTerm.setPromptText("Months");

        Label lblLnIntRate = new Label("Interest Rate");
        lblLnIntRate.setLayoutX(50);
        lblLnIntRate.setLayoutY(150);

        TextField txtLnIntRate = new TextField();
        txtLnIntRate.setLayoutX(210);
        txtLnIntRate.setLayoutY(150);
        txtLnIntRate.setPromptText("%");

        Label lblDownPay = new Label("Down Payment");
        lblDownPay.setLayoutX(50);
        lblDownPay.setLayoutY(200);

        TextField txtDownPay = new TextField();
        txtDownPay.setLayoutX(210);
        txtDownPay.setLayoutY(200);
        txtDownPay.setPromptText("$");

        Label lblTradeInValue = new Label("Trade-in Value");
        lblTradeInValue.setLayoutX(50);
        lblTradeInValue.setLayoutY(250);

        TextField txtTradeInValue = new TextField();
        txtTradeInValue.setLayoutX(210);
        txtTradeInValue.setLayoutY(250);
        txtTradeInValue.setPromptText("$");

        Label lblSalesTax = new Label("Sales Tax");
        lblSalesTax.setLayoutX(50);
        lblSalesTax.setLayoutY(300);

        TextField txtSalesTax = new TextField();
        txtSalesTax.setLayoutX(210);
        txtSalesTax.setLayoutY(300);
        txtSalesTax.setPromptText("$");

        Label lblFees = new Label("Title, Registration");
        lblFees.setLayoutX(50);
        lblFees.setLayoutY(350);

        Label lblFees2 = new Label("and Other Fees");
        lblFees2.setLayoutX(50);
        lblFees2.setLayoutY(370);

        TextField txtFees = new TextField();
        txtFees.setLayoutX(210);
        txtFees.setLayoutY(350);
        txtFees.setPromptText("$");

        CheckBox ln_cb1 = new CheckBox("Include All Fees in Loan");
        ln_cb1.setLayoutX(50);
        ln_cb1.setLayoutY(375);

        Button btnLoanCalculate = new Button("Calculate");
        btnLoanCalculate.setLayoutX(150);
        btnLoanCalculate.setLayoutY(430);
        btnLoanCalculate.setStyle("-fx-background-color: lightBlue; -fx-background-radius:10; -fx-font-size:20;");
        btnLoanCalculate.setOnMouseEntered(event -> btnLoanCalculate.setStyle("-fx-background-radius:10; -fx-font-size:20;" +
                " -fx-background-color:#3f72af; -fx-text-fill:white"));
        btnLoanCalculate.setOnMouseExited(event -> btnLoanCalculate.setStyle("-fx-font-size:20; -fx-background-radius:10; " +
                "-fx-background-color:lightBlue"));

        Label lblLnMonthlyPay = new Label(" Monthly Pay:");
        lblLnMonthlyPay.setLayoutX(500);
        lblLnMonthlyPay.setLayoutY(50);
        lblLnMonthlyPay.setStyle("-fx-background-color: lightGreen; -fx-min-width:400; -fx-font-size:20;");

        Label lblTotalLoan = new Label("Total Loan Amount");
        lblTotalLoan.setLayoutX(500);
        lblTotalLoan.setLayoutY(100);
        lblTotalLoan.setStyle(styleLbl);

        Label lblTotalLoanVal = new Label(" ");
        lblTotalLoanVal.setLayoutX(800);
        lblTotalLoanVal.setLayoutY(100);
        lblTotalLoanVal.setStyle(styleVal);

        Label lblTax = new Label("Sale Tax");
        lblTax.setLayoutX(500);
        lblTax.setLayoutY(150);
        lblTax.setStyle(styleLbl);

        Label lblTaxVal = new Label(" ");
        lblTaxVal.setLayoutX(800);
        lblTaxVal.setLayoutY(150);
        lblTaxVal.setStyle(styleVal);

        Label lblUpfront = new Label("Upfront Payment");
        lblUpfront.setLayoutX(500);
        lblUpfront.setLayoutY(200);
        lblUpfront.setStyle(styleLbl);

        Label lblUpfrontVal = new Label(" ");
        lblUpfrontVal.setLayoutX(800);
        lblUpfrontVal.setLayoutY(200);
        lblUpfrontVal.setStyle(styleVal);

        Label lblTotOfN = new Label("Total of _ Loan Payments");
        lblTotOfN.setLayoutX(500);
        lblTotOfN.setLayoutY(250);
        lblTotOfN.setStyle(styleLbl);

        Label lblTotOfNVal = new Label(" ");
        lblTotOfNVal.setLayoutX(800);
        lblTotOfNVal.setLayoutY(250);
        lblTotOfNVal.setStyle(styleVal);

        Label lblTotLoanInt = new Label("Total Loan Interest");
        lblTotLoanInt.setLayoutX(500);
        lblTotLoanInt.setLayoutY(300);
        lblTotLoanInt.setStyle(styleLbl);

        Label lblTotLoanIntVal = new Label(" ");
        lblTotLoanIntVal.setLayoutX(800);
        lblTotLoanIntVal.setLayoutY(300);
        lblTotLoanIntVal.setStyle(styleVal);

        Label lblTotCost = new Label("Total Cost (price, interest, tax, fees)");
        lblTotCost.setLayoutX(500);
        lblTotCost.setLayoutY(350);
        lblTotCost.setStyle(styleLbl);

        Label lblTotCostVal = new Label(" ");
        lblTotCostVal.setLayoutX(800);
        lblTotCostVal.setLayoutY(350);
        lblTotCostVal.setStyle(styleVal);

        Button btnLnBack = new Button("Back");
        btnLnBack.setLayoutX(800);
        btnLnBack.setLayoutY(620);
        btnLnBack.setStyle("-fx-background-color: lightblue; -fx-background-radius:10; -fx-font-size:20;");
        btnLnBack.setOnMouseEntered(event -> btnLnBack.setStyle("-fx-background-radius:10; -fx-font-size:20;" +
                " -fx-background-color:#3f72af; -fx-text-fill:white"));
        btnLnBack.setOnMouseExited(event -> btnLnBack.setStyle("-fx-font-size:20; -fx-background-radius:10; " +
                "-fx-background-color:lightBlue"));
        btnLnBack.setOnAction(e -> changeSceneAndName(primaryStage, sceneHome, "Home Page"));

        Pane windowLoan = new Pane();

        windowLoan.getChildren().addAll(lblLnTitle, lblAutoPrice, txtAutoPrice, lblLnLoanTerm, txtLnLoanTerm, lblLnIntRate, txtLnIntRate, lblDownPay, txtDownPay, lblTradeInValue,
                txtTradeInValue, lblSalesTax, txtSalesTax, lblFees, lblFees2, txtFees, btnLoanCalculate, lblLnMonthlyPay, lblTotalLoan, lblTotalLoanVal,
                lblTax, lblTaxVal, lblUpfront, lblUpfrontVal, lblTotOfN, lblTotOfNVal, lblTotLoanInt, lblTotLoanIntVal, lblTotCost, lblTotCostVal, btnLnBack);

        keyboard(txtAutoPrice, windowLoan);
        keyboard(txtLnLoanTerm, windowLoan);
        keyboard(txtLnIntRate, windowLoan);
        keyboard(txtDownPay, windowLoan);
        keyboard(txtTradeInValue, windowLoan);
        keyboard(txtSalesTax, windowLoan);
        keyboard(txtFees, windowLoan);

        btnLoanCalculate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                lblTotOfN.setText("Total of " + txtLnLoanTerm.getText() + " Loan Payments");

                double autoPrice = Double.parseDouble(txtAutoPrice.getText());
                double downPayment = Double.parseDouble(txtDownPay.getText());
                double tradeInValue = Double.parseDouble(txtTradeInValue.getText());
                double loan_PV = autoPrice - downPayment - tradeInValue;

                double loan_t = Double.parseDouble(txtLnLoanTerm.getText());
                double loan_interestRate = Double.parseDouble(txtLnIntRate.getText());
                double loan_r = (loan_interestRate * 0.01) / 12;

                double pmt;
                pmt = (loan_PV * loan_r * Math.pow((1 + loan_r), loan_t)) / (Math.pow((1 + loan_r), loan_t) - 1);

                lblLnMonthlyPay.setText(" Monthly Pay: $" + df.format(pmt));

                lblTotalLoanVal.setText("$ " + df.format(loan_PV));

                double salesTaxPercentage = Double.parseDouble(txtSalesTax.getText()) * 0.01;
                double salesTax = (autoPrice - tradeInValue) * salesTaxPercentage;
                lblTaxVal.setText("$ " + df.format(salesTax));

                double otherFees = Double.parseDouble(txtFees.getText());
                lblUpfrontVal.setText("$ " + df.format(downPayment + otherFees + salesTax));

                double noOfMonths = Double.parseDouble(txtLnLoanTerm.getText());
                double totalOfNMonths = pmt * noOfMonths;
                lblTotOfNVal.setText("$ " + df.format(totalOfNMonths));

                lblTotLoanIntVal.setText("$ " + df.format(totalOfNMonths - loan_PV));

                lblTotCostVal.setText("$ " + df.format(autoPrice + (totalOfNMonths - loan_PV) + salesTax + otherFees));

                output = "------------------Loan Calculation------------------ \n" +
                        "Auto Price : " + df.format(autoPrice) + "\n" +
                        "Loan Term : " + df.format(loan_t) + "\n" +
                        "Monthly Payment : " + pmt + "\n" +
                        "Interest Rate : " + loan_r + "\n" +
                        "Down Payment : " + downPayment + "\n" +
                        "-------------------------------------------------------- \n";

                out.write(output + "\n");
                out.flush();


            }
        });

        sceneLoan = new Scene(windowLoan, 1000, 800);


        //-----------------------------------------------------------Help-------------------------------------------------------------------------------------------


        Label inst = new Label("Instructions");
        inst.setLayoutX(425);
        inst.setLayoutY(20);
        inst.setStyle("-fx-font-size:30; -fx-text-fill:white; -fx-background-color:#3f72af; -fx-padding:5");

        Label instText = new Label("1.  Click the relevant button to navigate to the respective calculator \n" +
                "2.  Click on any text field for the keypad to pop-up \n" +
                "3.  Use the on-screen keypad or your keyboard to enter your details\n" +
                "4.  Do not enter $ mark or , \n" +
                "5.  Once all the details are correctly entered press Calculate to find your sum\n" +
                "\nFor Mortgage Calculator, \n" +
                "Leave one text field blank to calculate its value  \n" +
                "\nFor Finance Calculator, \n" +
                "If Simple Savings is selected from the drop down box,\n" +
                "Enter values for Principal Amount, Period, and Interest Rate ONLY \n" +
                "\nIf Compound Savings is selected from the drop down box, \n" +
                "Leave one text field blank to calculate its value\n" +
                "\nFor Auto Loan Calculator, \n" +
                "Fill in all the text fields to calculate the monthly pay");
        instText.setLayoutX(150);
        instText.setLayoutY(110);
        instText.setStyle("-fx-font-size:16");

        Button btnHelpBack = new Button("Back");
        btnHelpBack.setLayoutX(850);
        btnHelpBack.setLayoutY(700);
        btnHelpBack.setStyle("-fx-background-color: lightblue; -fx-background-radius:10; -fx-font-size:20;");
        btnHelpBack.setOnMouseEntered(event -> btnHelpBack.setStyle("-fx-background-radius:10; -fx-font-size:20;" +
                " -fx-background-color:#3f72af; -fx-text-fill:white"));
        btnHelpBack.setOnMouseExited(event -> btnHelpBack.setStyle("-fx-font-size:20; -fx-background-radius:10; " +
                "-fx-background-color:lightBlue"));
        btnHelpBack.setOnAction(e -> primaryStage.setScene(sceneHome));

        Pane windowHelp = new Pane();

        windowHelp.getChildren().addAll(inst, instText, btnHelpBack);

        sceneHelp = new Scene(windowHelp, 1000, 800);


        //-------------------------------------------------------------------History-----------------------------------------------------------------------------------------------


        Label lblHistory = new Label("History");
        lblHistory.setLayoutX(450);
        lblHistory.setLayoutY(100);
        lblHistory.setStyle("-fx-font-size : 20; -fx-font-weight:bold");

        TextArea lblAreaHistory = new TextArea();
        lblAreaHistory.setLayoutX(100);
        lblAreaHistory.setLayoutY(200);
        lblAreaHistory.setPrefSize(800, 300);
        lblAreaHistory.setStyle("-fx-border-color: lightBlue; -fx-border-width:10;");

        Button btnRefresh = new Button("Refresh");
        btnRefresh.setLayoutX(750);
        btnRefresh.setLayoutY(700);
        btnRefresh.setStyle("-fx-background-color: lightBlue; -fx-background-radius:10; -fx-font-size:20;");
        btnRefresh.setOnMouseEntered(event -> btnRefresh.setStyle("-fx-background-radius:10; -fx-font-size:20;" +
                " -fx-background-color:#3f72af; -fx-text-fill:white"));
        btnRefresh.setOnMouseExited(event -> btnRefresh.setStyle("-fx-font-size:20; -fx-background-radius:10; " +
                "-fx-background-color:lightBlue"));

        btnRefresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String data = "";
                String current = "";
                Scanner scanner = null;
                try {
                    scanner = new Scanner(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                while (scanner.hasNextLine()) {
                    current = scanner.nextLine();
                    data = data.concat(current + "\n");
                }
                scanner.close();
                lblAreaHistory.setText(data);
            }
        });

        Button btnHistoryBack = new Button("Back");
        btnHistoryBack.setLayoutX(850);
        btnHistoryBack.setLayoutY(700);
        btnHistoryBack.setStyle("-fx-background-color: lightBlue; -fx-background-radius:10; -fx-font-size:20;");
        btnHistoryBack.setOnMouseEntered(event -> btnHistoryBack.setStyle("-fx-background-radius:10; -fx-font-size:20;" +
                " -fx-background-color:#3f72af; -fx-text-fill:white"));
        btnHistoryBack.setOnMouseExited(event -> btnHistoryBack.setStyle("-fx-font-size:20; -fx-background-radius:10; " +
                "-fx-background-color:lightBlue"));
        btnHistoryBack.setOnAction(e -> primaryStage.setScene(sceneHome));

        Pane windowHistory = new Pane();

        windowHistory.getChildren().addAll(lblHistory, lblAreaHistory, btnRefresh, btnHistoryBack);

        sceneHistory = new Scene(windowHistory, 1000, 800);

    }





        //---------------------------------------------------------------Onscreen Keyboard-----------------------------------------------------------------------------------------


    static void keyboard(TextField x, Pane paneKB) {
        x.setOnMousePressed(new EventHandler<MouseEvent>() {
            int count = 0;

            @Override
            public void handle(MouseEvent event) {

                int axisX = 400;
                int axisY = 450;

                Button btnKB1 = new Button("1");
                btnKB1.setLayoutX(axisX);
                btnKB1.setLayoutY(axisY);
                btnKB1.setPrefSize(50, 50);
                btnKB1.setStyle("-fx-background-color:lightBlue; -fx-background-radius:30; -fx-font-size:20;");
                btnKB1.setOnMouseEntered(event1 -> btnKB1.setStyle("-fx-background-radius:30; -fx-font-size:20;" +
                        " -fx-background-color:#3f72af; -fx-text-fill:white"));
                btnKB1.setOnMouseExited(event1 -> btnKB1.setStyle("-fx-font-size:20; -fx-background-radius:30; " +
                        "-fx-background-color:lightBlue;"));

                btnKB1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String text = x.getText();
                        x.setText(text + "1");
                    }
                });

                Button btnKB2 = new Button("2");
                btnKB2.setLayoutX(axisX + 53);
                btnKB2.setLayoutY(axisY);
                btnKB2.setPrefSize(50, 50);
                btnKB2.setStyle("-fx-background-color:lightBlue; -fx-background-radius:30; -fx-font-size:20;");
                btnKB2.setOnMouseEntered(event1 -> btnKB2.setStyle("-fx-background-radius:30; -fx-font-size:20;" +
                        " -fx-background-color:#3f72af; -fx-text-fill:white"));
                btnKB2.setOnMouseExited(event1 -> btnKB2.setStyle("-fx-font-size:20; -fx-background-radius:30; " +
                        "-fx-background-color:lightBlue;"));

                btnKB2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String text = x.getText();
                        x.setText(text + "2");
                    }
                });

                Button btnKB3 = new Button("3");
                btnKB3.setLayoutX(axisX + 106);
                btnKB3.setLayoutY(axisY);
                btnKB3.setPrefSize(50, 50);
                btnKB3.setStyle("-fx-background-color:lightBlue; -fx-background-radius:30; -fx-font-size:20;");
                btnKB3.setOnMouseEntered(event1 -> btnKB3.setStyle("-fx-background-radius:30; -fx-font-size:20;" +
                        " -fx-background-color:#3f72af; -fx-text-fill:white"));
                btnKB3.setOnMouseExited(event1 -> btnKB3.setStyle("-fx-font-size:20; -fx-background-radius:30; " +
                        "-fx-background-color:lightBlue;"));

                btnKB3.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String text = x.getText();
                        x.setText(text + "3");
                    }
                });

                Button btnKB4 = new Button("4");
                btnKB4.setLayoutX(axisX);
                btnKB4.setLayoutY(axisY + 53);
                btnKB4.setPrefSize(50, 50);
                btnKB4.setStyle("-fx-background-color:lightBlue; -fx-background-radius:30; -fx-font-size:20;");
                btnKB4.setOnMouseEntered(event1 -> btnKB4.setStyle("-fx-background-radius:30; -fx-font-size:20;" +
                        " -fx-background-color:#3f72af; -fx-text-fill:white"));
                btnKB4.setOnMouseExited(event1 -> btnKB4.setStyle("-fx-font-size:20; -fx-background-radius:30; " +
                        "-fx-background-color:lightBlue;"));

                btnKB4.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String text = x.getText();
                        x.setText(text + "4");
                    }
                });

                Button btnKB5 = new Button("5");
                btnKB5.setLayoutX(axisX + 53);
                btnKB5.setLayoutY(axisY + 53);
                btnKB5.setPrefSize(50, 50);
                btnKB5.setStyle("-fx-background-color:lightBlue; -fx-background-radius:30; -fx-font-size:20;");
                btnKB5.setOnMouseEntered(event1 -> btnKB5.setStyle("-fx-background-radius:30; -fx-font-size:20;" +
                        " -fx-background-color:#3f72af; -fx-text-fill:white"));
                btnKB5.setOnMouseExited(event1 -> btnKB5.setStyle("-fx-font-size:20; -fx-background-radius:30; " +
                        "-fx-background-color:lightBlue;"));

                btnKB5.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String text = x.getText();
                        x.setText(text + "5");
                    }
                });

                Button btnKB6 = new Button("6");
                btnKB6.setLayoutX(axisX + 106);
                btnKB6.setLayoutY(axisY + 53);
                btnKB6.setPrefSize(50, 50);
                btnKB6.setStyle("-fx-background-color:lightBlue; -fx-background-radius:30; -fx-font-size:20;");
                btnKB6.setOnMouseEntered(event1 -> btnKB6.setStyle("-fx-background-radius:30; -fx-font-size:20;" +
                        " -fx-background-color:#3f72af; -fx-text-fill:white"));
                btnKB6.setOnMouseExited(event1 -> btnKB6.setStyle("-fx-font-size:20; -fx-background-radius:30; " +
                        "-fx-background-color:lightBlue;"));

                btnKB6.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String text = x.getText();
                        x.setText(text + "6");
                    }
                });

                Button btnKB7 = new Button("7");
                btnKB7.setLayoutX(axisX);
                btnKB7.setLayoutY(axisY + 106);
                btnKB7.setPrefSize(50, 50);
                btnKB7.setStyle("-fx-background-color:lightBlue; -fx-background-radius:30; -fx-font-size:20;");
                btnKB7.setOnMouseEntered(event1 -> btnKB7.setStyle("-fx-background-radius:30; -fx-font-size:20;" +
                        " -fx-background-color:#3f72af; -fx-text-fill:white"));
                btnKB7.setOnMouseExited(event1 -> btnKB7.setStyle("-fx-font-size:20; -fx-background-radius:30; " +
                        "-fx-background-color:lightBlue;"));


                btnKB7.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String text = x.getText();
                        x.setText(text + "7");
                    }
                });

                Button btnKB8 = new Button("8");
                btnKB8.setLayoutX(axisX + 53);
                btnKB8.setLayoutY(axisY + 106);
                btnKB8.setPrefSize(50, 50);
                btnKB8.setStyle("-fx-background-color:lightBlue; -fx-background-radius:30; -fx-font-size:20;");
                btnKB8.setOnMouseEntered(event1 -> btnKB8.setStyle("-fx-background-radius:30; -fx-font-size:20;" +
                        " -fx-background-color:#3f72af; -fx-text-fill:white"));
                btnKB8.setOnMouseExited(event1 -> btnKB8.setStyle("-fx-font-size:20; -fx-background-radius:30; " +
                        "-fx-background-color:lightBlue;"));

                btnKB8.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String text = x.getText();
                        x.setText(text + "8");
                    }
                });

                Button btnKB9 = new Button("9");
                btnKB9.setLayoutX(axisX + 106);
                btnKB9.setLayoutY(axisY + 106);
                btnKB9.setPrefSize(50, 50);
                btnKB9.setStyle("-fx-background-color:lightBlue; -fx-background-radius:30; -fx-font-size:20;");
                btnKB9.setOnMouseEntered(event1 -> btnKB9.setStyle("-fx-background-radius:30; -fx-font-size:20;" +
                        " -fx-background-color:#3f72af; -fx-text-fill:white"));
                btnKB9.setOnMouseExited(event1 -> btnKB9.setStyle("-fx-font-size:20; -fx-background-radius:30; " +
                        "-fx-background-color:lightBlue;"));

                btnKB9.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String text = x.getText();
                        x.setText(text + "9");
                    }
                });

                Button btnKB0 = new Button("0");
                btnKB0.setLayoutX(axisX + 53);
                btnKB0.setLayoutY(axisY + 159);
                btnKB0.setPrefSize(50, 50);
                btnKB0.setStyle("-fx-background-color:lightBlue; -fx-background-radius:30; -fx-font-size:20;");
                btnKB0.setOnMouseEntered(event1 -> btnKB0.setStyle("-fx-background-radius:30; -fx-font-size:20;" +
                        " -fx-background-color:#3f72af; -fx-text-fill:white"));
                btnKB0.setOnMouseExited(event1 -> btnKB0.setStyle("-fx-font-size:20; -fx-background-radius:30; " +
                        "-fx-background-color:lightBlue;"));

                btnKB0.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String text = x.getText();
                        x.setText(text + "0");
                    }
                });

                Button btnDeci = new Button(".");
                btnDeci.setLayoutX(axisX);
                btnDeci.setLayoutY(axisY + 159);
                btnDeci.setPrefSize(50, 50);
                btnDeci.setStyle("-fx-background-color:lightBlue; -fx-background-radius:30; -fx-font-size:20;");
                btnDeci.setOnMouseEntered(event1 -> btnDeci.setStyle("-fx-background-radius:30; -fx-font-size:13;" +
                        " -fx-background-color:#3f72af; -fx-text-fill:white"));
                btnDeci.setOnMouseExited(event1 -> btnDeci.setStyle("-fx-font-size:20; -fx-background-radius:30; " +
                        "-fx-background-color:lightBlue;"));

                btnDeci.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (count == 0){
                            String text = x.getText();
                            x.setText(text + ".");
                            count++;
                        }
                    }
                });


                Button btnBack = new Button("←");
                btnBack.setLayoutX(axisX + 106);
                btnBack.setLayoutY(axisY + 159);
                btnBack.setPrefSize(50, 50);
                btnBack.setStyle("-fx-background-color:lightBlue; -fx-background-radius:30; -fx-font-size:20;");
                btnBack.setOnMouseEntered(event1 -> btnBack.setStyle("-fx-background-radius:30; -fx-font-size:20;" +
                        " -fx-background-color:#3f72af; -fx-text-fill:white"));
                btnBack.setOnMouseExited(event1 -> btnBack.setStyle("-fx-font-size:20; -fx-background-radius:30; " +
                        "-fx-background-color:lightBlue;"));

                btnBack.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String text = x.getText();
                        x.setText(text.substring(0, text.length() - 1));
                    }
                });

                paneKB.getChildren().addAll(btnKB1, btnKB2, btnKB3, btnKB4, btnKB5, btnKB6, btnKB7, btnKB8, btnKB9, btnKB0, btnDeci, btnBack);

            }
        });

    }

    static void changeSceneAndName(Stage stage, Scene scene, String title){
        stage.setScene(scene);
        stage.setTitle(title);
    }



        public static void main (String[]args){
            launch(args);
        }
}

