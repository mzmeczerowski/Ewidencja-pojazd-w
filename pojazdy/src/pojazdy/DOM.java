package pojazdy;

import java.io.FileOutputStream;

// rejestr do tworzenia implementacji DOM
import org.w3c.dom.bootstrap.DOMImplementationRegistry;

// Implementacja DOM Level 3 Load & Save
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSParser; // Do serializacji (zapisywania) dokumentow
import org.w3c.dom.ls.LSSerializer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.w3c.dom.ls.LSOutput;

// Konfigurator i obsluga bledow
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMError;
import org.w3c.dom.DOMErrorHandler;

// Do pracy z dokumentem
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOM extends Application{
	public static Document document;
	    
	public void start(Stage primaryStage)
	{
		VBox root = new VBox();
		root.getStyleClass().add("vbox");
	        
	    String args[] = new String[1];
	    
	    													args[0] = "new_Pojazdy.xml";
		
	    if (args[0] == null)
	    {
			printUsage();
			System.exit(1);
		}
	    Label tytul = new Label("Dodawanie pojazdu");
	    TextField typ = new TextField(); typ.setPromptText("typ");
	    TextField marka = new TextField(); marka.setPromptText("marka");
	    TextField model = new TextField(); model.setPromptText("model");
	    TextField rokProdukcji = new TextField(); rokProdukcji.setPromptText("rok produkcji");
	    TextField stanLicznika = new TextField(); stanLicznika.setPromptText("stan licznika");
	    TextField numerRejestracyjny = new TextField(); numerRejestracyjny.setPromptText("numer rejestracyjny");
	    TextField numerVin = new TextField(); numerVin.setPromptText("numer vin");
	    TextField masaWlasna = new TextField(); masaWlasna.setPromptText("masa własna");
	    TextField pojemnosc = new TextField(); pojemnosc.setPromptText("pojemnosc silnika");
	    TextField kolor = new TextField(); kolor.setPromptText("kolor");
	    HBox uoc = new HBox();
		    Label oc= new Label("Ubezpieczenie oc: ");
		    TextField ocDo = new TextField(); ocDo.setPromptText("do kiedy ważne");
		    TextField ocKoszt = new TextField(); ocKoszt.setPromptText("koszt");
		    TextField ocNazwaUbezpieczyciela = new TextField(); ocNazwaUbezpieczyciela.setPromptText("nazwa ubezpieczyciela");
		    uoc.getChildren().addAll(oc,ocDo,ocKoszt, ocNazwaUbezpieczyciela);
		HBox uac = new HBox();
			CheckBox checkac = new CheckBox("ac");
			TextField acDo = new TextField(); acDo.setPromptText("do kiedy ważne");
		    TextField acKoszt = new TextField(); acKoszt.setPromptText("koszt");
		    TextField acKwota = new TextField(); acKwota.setPromptText("kwota ubezpieczenia");
		    TextField acNazwaUbezpieczyciela = new TextField(); acNazwaUbezpieczyciela.setPromptText("nazwa ubezpieczyciela");
			checkac.setOnAction(e ->
			{
				if (checkac.isSelected())
				{
					uac.getChildren().addAll(acDo, acKoszt, acKwota, acNazwaUbezpieczyciela);
				}
				else
					uac.getChildren().removeAll(acDo, acKoszt, acKwota, acNazwaUbezpieczyciela);
				
			});
			uac.getChildren().addAll(checkac);
		HBox uassistance = new HBox();
			CheckBox checkassistance = new CheckBox("assistance");
			TextField assistanceDo = new TextField(); assistanceDo.setPromptText("do kiedy ważne");
		    TextField assistanceKoszt = new TextField(); assistanceKoszt.setPromptText("koszt");
		    TextField assistanceDoIluKm = new TextField(); assistanceDoIluKm.setPromptText("do ilu km cholowanie");
		    CheckBox assistanceZastepczySamochod = new CheckBox("czy zastepczy samochód?"); 
		    TextField assistanceNazwaUbezpieczyciela = new TextField(); assistanceNazwaUbezpieczyciela.setPromptText("nazwa ubezpieczyciela");
			checkassistance.setOnAction(e ->
			{
				if (checkassistance.isSelected())
				{
					uassistance.getChildren().addAll(assistanceDo, assistanceKoszt, assistanceDoIluKm, assistanceNazwaUbezpieczyciela, assistanceZastepczySamochod);
				}
				else
					uassistance.getChildren().removeAll(assistanceDo, assistanceKoszt, assistanceDoIluKm, assistanceZastepczySamochod, assistanceNazwaUbezpieczyciela);
				
			});
			uassistance.getChildren().addAll(checkassistance);
			
			
			
	    root.getChildren().addAll(tytul, typ, marka, model, rokProdukcji,
	    		stanLicznika, numerRejestracyjny, numerVin,
	    		masaWlasna, pojemnosc, kolor, uoc, uac, uassistance
	    		);
	    
	    Button btn = new Button("Akcja...");
		root.getChildren().add(btn);
        btn.setOnAction(e -> 
        {
		try {
			/*
			 * ustawienie systemowej wlasnosci (moze byc dokonane w innym
			 * miejscu, pliku konfiguracyjnym w systemie itp.) konkretna
			 * implementacja DOM
			 */
			System.setProperty(DOMImplementationRegistry.PROPERTY,
					"org.apache.xerces.dom.DOMXSImplementationSourceImpl");
			DOMImplementationRegistry registry = DOMImplementationRegistry
					.newInstance();

			// pozyskanie implementacji Load & Save DOM Level 3 z rejestru
			DOMImplementationLS impl = (DOMImplementationLS) registry
					.getDOMImplementation("LS");

			// stworzenie DOMBuilder
			LSParser builder = impl.createLSParser(
					DOMImplementationLS.MODE_SYNCHRONOUS, null);

			// pozyskanie konfiguratora - koniecznie zajrzec do dokumentacji co
			// mozna poustawiac
			DOMConfiguration config = builder.getDomConfig();

			// stworzenie DOMErrorHandler i zarejestrowanie w konfiguratorze
			DOMErrorHandler errorHandler = getErrorHandler();
			config.setParameter("error-handler", errorHandler);

			// set validation feature
			config.setParameter("validate", Boolean.TRUE);

			// set schema language
			config.setParameter("schema-type",
					"http://www.w3.org/2001/XMLSchema");

			// set schema location
			config.setParameter("schema-location", "Pojazdy.xsd");

			System.out.println("Parsowanie " + args[0] + "...");
			// sparsowanie dokumentu i pozyskanie "document" do dalszej pracy
			document = builder.parseURI(args[0]);

			// praca z dokumentem, modyfikacja zawartosci etc... np.
			// tutaj dodanie nowego pracownika poprzez skopiowanie innego
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
				NodeList nodelist = document.getDocumentElement().getChildNodes();
				Node node = null;
				for (int j=0; j < nodelist.getLength(); j++)
				{
					node = nodelist.item(j);
					if (node.getNodeName().equals("pojazd"))
					{
						break;
					}
				}
				
				Element elem = (Element) node;
				Element newElem = (Element) elem.cloneNode(true);
				newElem.setAttribute("typ", typ.getText());
				NodeList nl = newElem.getChildNodes();
				for (int i = 0; i < nl.getLength(); i++) {
					Node n = nl.item(i);
					if (n.getNodeName().equals("marka")) n.setTextContent(marka.getText());
					if (n.getNodeName().equals("model")) n.setTextContent(model.getText());
					if (n.getNodeName().equals("rok_produkcji")) n.setTextContent(rokProdukcji.getText());
					if (n.getNodeName().equals("stan_licznika")) n.setTextContent(stanLicznika.getText());
					if (n.getNodeName().equals("numer_rejestracyjny")) n.setTextContent(numerRejestracyjny.getText());
					if (n.getNodeName().equals("numer_vin")) n.setTextContent(numerVin.getText());
					if (n.getNodeName().equals("masa_wlasna")) n.setTextContent(masaWlasna.getText());
					if (n.getNodeName().equals("pojemnosc")) n.setTextContent(pojemnosc.getText());
					if (n.getNodeName().equals("kolor")) n.setTextContent(kolor.getText());
					if (n.getNodeName().equals("ubezpieczenie")) {
						NodeList nll = n.getChildNodes();
						for (int j = 0; j < nll.getLength(); j++) {
							Node nn = nll.item(j);
							if (nn.getNodeName().equals("oc")) {
								NodeList nlll = nn.getChildNodes();
								for (int k = 0; k < nlll.getLength(); k++) {
									Node nnn = nlll.item(k);
									if (nnn.getNodeName().equals("do")) nnn.setTextContent(ocDo.getText());
									if (nnn.getNodeName().equals("koszt")) nnn.setTextContent(ocKoszt.getText());
									if (nnn.getNodeName().equals("nazwa_ubezpieczyciela")) nnn.setTextContent(ocNazwaUbezpieczyciela.getText());
								}
							}
							if (nn.getNodeName().equals("ac")) {
								NodeList nlll = nn.getChildNodes();
								if (!checkac.isSelected()) {
									nn.getParentNode().removeChild(nn);
									continue;
								}
								for (int k = 0; k < nlll.getLength(); k++) {
									Node nnn = nlll.item(k);
									if (nnn.getNodeName().equals("do")) nnn.setTextContent(acDo.getText());
									if (nnn.getNodeName().equals("koszt")) nnn.setTextContent(acKoszt.getText());
									if (nnn.getNodeName().equals("kwota_ubezpieczenia")) nnn.setTextContent(acKwota.getText());
									if (nnn.getNodeName().equals("nazwa_ubezpieczyciela")) nnn.setTextContent(acNazwaUbezpieczyciela.getText());
								}
							}
							if (nn.getNodeName().equals("assistance")) {
								NodeList nlll = nn.getChildNodes();
								if (!checkassistance.isSelected()) {
									nn.getParentNode().removeChild(nn);
									continue;
								}
								for (int k = 0; k < nlll.getLength(); k++) {
									Node nnn = nlll.item(k);
									if (nnn.getNodeName().equals("do")) nnn.setTextContent(assistanceDo.getText());
									if (nnn.getNodeName().equals("koszt")) nnn.setTextContent(assistanceKoszt.getText());
									if (nnn.getNodeName().equals("do_ilu_km")) nnn.setTextContent(assistanceDoIluKm.getText());
									String czySamochod = null;
									if(assistanceZastepczySamochod.isSelected()) czySamochod="tak"; else czySamochod="nie";
									if (nnn.getNodeName().equals("zastepczy_samochod")) nnn.setTextContent(czySamochod);
									if (nnn.getNodeName().equals("nazwa_ubezpieczyciela")) nnn.setTextContent(assistanceNazwaUbezpieczyciela.getText());
								}
							}
						}
					}
				}
				node.insertBefore(newElem, null);
			
	        
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			// pozyskanie serializatora
			LSSerializer domWriter = impl.createLSSerializer();
			// pobranie konfiguratora dla serializatora
			config = domWriter.getDomConfig();
			config.setParameter("xml-declaration", Boolean.TRUE);

			// pozyskanie i konfiguracja Wyjscia
			LSOutput dOut = impl.createLSOutput();
			dOut.setEncoding("utf-8");
			dOut.setByteStream(new FileOutputStream("new_" + args[0]));

			System.out.println("Serializing document... ");
			domWriter.write(document, dOut);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		});
        
        root.getStylesheets().add("style.css");
        primaryStage.setTitle("Controler");
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();
	}

	private static void printUsage() {

		System.err.println("usage: java Dom3Demo uri");
		System.err.println();
		System.err
				.println("NOTE: You can only validate DOM tree against XML Schemas.");

	}

	// obsluga bledow za pomoca anonimowej klasy wewnetrznej implementujacej
	// DOMErrorHandler
	// por. SAX ErrorHandler
	public static DOMErrorHandler getErrorHandler() {
		return new DOMErrorHandler() {
			public boolean handleError(DOMError error) {
				short severity = error.getSeverity();
				if (severity == error.SEVERITY_ERROR) {
					System.out.println("[dom3-error]: " + error.getMessage());
				}
				if (severity == error.SEVERITY_WARNING) {
					System.out.println("[dom3-warning]: " + error.getMessage());
				}
				if (severity == error.SEVERITY_FATAL_ERROR) {
					System.out.println("[dom3-fatal-error]: "
							+ error.getMessage());
				}
				return true;
			}
		};
	}

    public static void main(String[] args) {
        launch(args);
    }
}