import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

    public class SmartCityProject_of_CHANDIGARH {
        private JFrame frame;
        private JButton showButton;
        private JComboBox<String> categoryComboBox;
        private JTextArea infoTextArea;

        private boolean isAuthenticated = false;
        private String currentUser;
        private Map<String, ArrayList<Place>> places = new HashMap<>();

        public SmartCityProject_of_CHANDIGARH() {
            frame = new JFrame("Smart City Project");

            showButton = new JButton("Show");
            showButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String selectedCategory = (String) categoryComboBox.getSelectedItem();
                    showPlaces(selectedCategory);
                }
            });
            categoryComboBox = new JComboBox<>(new String[]{"Hotels", "Restaurants", "Temples", "Tourist Places"});
            categoryComboBox.setFont(new Font("Arial", Font.PLAIN, 12));
            categoryComboBox.setForeground(Color.BLUE);

            JButton loginButton = new JButton("Login");
            loginButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    showLoginDialog();
                }
            });

            JButton logoutButton = new JButton("Logout");
            logoutButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    isAuthenticated = false;
                    currentUser = null;
                    infoTextArea.setText("");
                    JOptionPane.showMessageDialog(frame, "Logged out successfully.");
                }
            });

            infoTextArea = new JTextArea(10, 30);
            infoTextArea.setEditable(false);
            infoTextArea.setFont(new Font("Arial", Font.BOLD, 14));
            infoTextArea.setForeground(Color.BLACK);
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(loginButton);
            buttonPanel.add(logoutButton);
            buttonPanel.add(categoryComboBox);
            buttonPanel.add(showButton);

            frame.add(buttonPanel, BorderLayout.NORTH);
            frame.add(new JScrollPane(infoTextArea), BorderLayout.CENTER);

            frame.setSize(400, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }

        private void showLoginDialog() {
            String username = JOptionPane.showInputDialog(frame, "Enter username:");
            if (username != null && !username.isEmpty()) {
                isAuthenticated = true;
                currentUser = username;
                JOptionPane.showMessageDialog(frame, "Welcome, " + username + "!");
            }
        }

        private void showPlaces(String category) {
            if (!isAuthenticated) {
                JOptionPane.showMessageDialog(frame, "Please log in first.");
                return;
            }

            infoTextArea.setText(category + ":\n");
            ArrayList<Place> categoryPlaces = places.get(category);
            if (categoryPlaces != null) {
                for (Place place : categoryPlaces) {
                    infoTextArea.append(place.getName() + " - " + place.getAddress() + "\n");

                    if (category.equals("Restaurants")) {
                        // Display restaurant menu details
                        infoTextArea.append("Veg Menu:\n");
                        for (String vegItem : place.getVegMenu()) {
                            infoTextArea.append("  " + vegItem + "\n");
                        }

                        infoTextArea.append("Non-Veg Menu:\n");
                        for (String nonVegItem : place.getNonVegMenu()) {
                            infoTextArea.append("  " + nonVegItem + "\n");
                        }

                        infoTextArea.append("Drinks Menu:\n");
                        for (String drinkItem : place.getDrinksMenu()) {
                            infoTextArea.append("  " + drinkItem + "\n");
                        }

                        // Display restaurant contact details
                        infoTextArea.append("Contact: " + place.getContact() + "\n");
                    } else if (category.equals("Hotels")) {
                        // Display hotel price, facilities, and contact details
                        infoTextArea.append("Price: Rs." + place.getPrice() + "\n");
                        infoTextArea.append("Facilities: " + String.join(", ", place.getFacilities()) + "\n");
                        infoTextArea.append("Contact: " + place.getContact() + "\n");
                    } else if (category.equals("Temples")) {
                        // Display temple opening hours
                        infoTextArea.append("Opening Hours: " + place.getTime() + "\n");
                    } else if (category.equals("Tourist Places")) {
                        // Display tourist place entry fees
                        infoTextArea.append("Entry Fees: Rs." + place.getEntryFees() + "\n");
                    }
                }
            } else {
                infoTextArea.append("No " + category + " available.\n");
            }
        }

        static class Place {
            private String name;
            private String address;
            private double price;
            private String time;
            private double entryFees;
            private List<String> vegMenu;
            private List<String> nonVegMenu;
            private List<String> drinksMenu;
            private List<String> facilities;
            private String contact;  // New attribute for contact details

            public Place(String name, String address, double price, List<String> facilities, String contact) {
                this.name = name;
                this.address = address;
                this.price = price;
                this.facilities = facilities;
                this.contact = contact;
            }

            public Place(String name, String address, List<String> vegMenu, List<String> nonVegMenu, List<String> drinksMenu, String contact) {
                this.name = name;
                this.address = address;
                this.vegMenu = vegMenu;
                this.nonVegMenu = nonVegMenu;
                this.drinksMenu = drinksMenu;
                this.contact = contact;
            }

            public Place(String name, String address, String time) {
                this.name = name;
                this.address = address;
                this.time = time;
            }

            public Place(String name, String address, double entryFees) {
                this.name = name;
                this.address = address;
                this.entryFees = entryFees;
            }

            public String getName() {
                return name;
            }

            public String getAddress() {
                return address;
            }

            public double getPrice() {
                return price;
            }

            public List<String> getVegMenu() {
                return vegMenu;
            }

            public List<String> getNonVegMenu() {
                return nonVegMenu;
            }

            public List<String> getDrinksMenu() {
                return drinksMenu;
            }

            public String getTime() {
                return time;
            }

            public double getEntryFees() {
                return entryFees;
            }

            public List<String> getFacilities() {
                return facilities;
            }

            public String getContact() {
                return contact;
            }
        }

        public static void main(String[] args) {
            SmartCityProject_of_CHANDIGARH smartCityProject = new SmartCityProject_of_CHANDIGARH();

            // Adding details for Hotels with facilities and contact details
            ArrayList<Place> hotels = new ArrayList<>();
            List<String> hotelFacilities1 = Arrays.asList("Free Wi-Fi ", "Spa ", "Wi-Fi  , Parking ");
            hotels.add(new Place("Hotel Aroma Chandigarh", "Himalya Marg,Sector 22C, chandigarh,160022", 3569, hotelFacilities1, "01722728713"));

            List<String> hotelFacilities2 = Arrays.asList("Free Breakfast ", "pool ", "Air Conditioning ", " Wi-Fi ");
            hotels.add(new Place("Hotel Sunbeam Premium ", "Sco 1054 , 1057,Udyog Path , Sector 22B , Sector 22 , Chandigarh , 160022", 3499, hotelFacilities2, "0173522233"));
            List<String> hotelFacilities3 = Arrays.asList("Free Breakfast", "Spa", "Conference Rooms");
            hotels.add(new Place("Hotel Shivalikview", "Jan Marg , opp. Sampark 17E , Sector 17,Chandigah, 160017", 200.00, hotelFacilities3, "9876543210"));
            List<String> hotelFacilities4= Arrays.asList("Free Breakfast", "Spa", "Conference Rooms");
            hotels.add(new Place(" Hotel Icon", " Sector, 10C, Sector 10, Chandigarh, 160011", 200.00, hotelFacilities4, "0923412331"));
            List<String> hotelFacilities5= Arrays.asList("Free Breakfast", "Spa", "Conference Rooms");


            // Adding details for Restaurants with contact details
            ArrayList<Place> restaurants = new ArrayList<>();
            List<String> vegMenu1 = Arrays.asList("Mix  Veg  - Rs.150", "Dal Makhani - Rs.200");
            List<String> nonVegMenu1 = Arrays.asList("Chicken Roll - Rs.350", "Kadhai Chicken - Rs.450");
            List<String> drinksMenu1 = Arrays.asList("Chocolate Shake - Rs.90", "Cold Coffee - Rs.150");

            restaurants.add(new Place("The Eating House", " PQJF+2C9, Sector 22 Market Rd, Sector 22C, Sector 22, Chandigarh, 160022", vegMenu1, nonVegMenu1, drinksMenu1, "0172272334"));

            List<String> vegMenu2 = Arrays.asList("Soya Chap - Rs.250", "Kadhai Paneer - Rs.150");
            List<String> nonVegMenu2 = Arrays.asList("Chicken kuri- Rs.200", "Mutton - Rs.300");
            List<String> drinksMenu2= Arrays.asList("Masala Chai - Rs.50", "Coffee - Rs.40");
            restaurants.add(new Place("Cloud 9", "6th Floor ( Rooftop, 17E, Jan Marg, 17C, Chandigarh, 160017", vegMenu2, nonVegMenu2, drinksMenu2, "2534234477"));
            //  restaurants.add(new Place("Restaurant X", "321 Pine Lane", vegMenu, nonVegMenu, drinksMenuX, "456-789-0123"));
            List<String> vegMenu3 = Arrays.asList("Rajma Chawal - Rs.150", "Shahi Paneer - Rs.300");
            List<String> nonVegMenu3 = Arrays.asList("Chicken Tikka - Rs.170", "Kadhai Chicken - Rs.200");
            List<String> drinksMenu3 = Arrays.asList("Cold Coffee - Rs.90", "Oreo Shake - Rs.100");

            // restaurants.add(new Place("Restaurant X", "321 Pine Lane", vegMenuY, nonVegMenuY, drinksMenuY, "456-789-0123"));restaurants.add(new Place("Restaurant X", "321 Pine Lane", vegMenu3, nonVegMenu3, drinksMenu3, "456-789-0123"));


            restaurants.add(new Place("Garlic and Greens", "178-178A, Purv Marg, Industrial Area Phase I, Chandigarh, 160002", vegMenu3, nonVegMenu3, drinksMenu3, "0321456332"));
            //restaurants.add(new Place("Restaurant X", "321 Pine Lane", vegMenuX, nonVegMenuX, drinksMenuX, "456-789-0123"));
            List<String> vegMenu4 = Arrays.asList("Matar Paneer - Rs.150", "Mix Veg - Rs.200");
            List<String> nonVegMenu4 = Arrays.asList("Chicken Roll - Rs.230", "Chicken Kari - Rs.130");
            List<String> drinksMenu4 = Arrays.asList("Chocolate Shake - Rs.90", "Coffee- Rs.80");

            restaurants.add(new Place("Tawa Restaurant ", "SCO 9-10, Sector 19C, Sector 19, Chandigarh, 160019", vegMenu4, nonVegMenu4, drinksMenu4, "0126473532"));
            //restaurants.add(new Place("Restaurant X", "321 Pine Lane", vegMenuX, nonVegMenuX, drinksMenuX, "456-789-0123"));

            // ... (Add more restaurants with contact details)

            // Adding details for Temples
            // ("Chandi Devi Temple", "Address21", "6AM to 8:00PM"));
            ArrayList<Place> temples = new ArrayList<>();
            temples.add(new Place("Shiv Temple", "1401, Udyan Path, 37B, Sector 37, Chandigarh, 160036", "5AM to 8:30PM"));
            // ArrayList<Place> temples = new ArrayList<>();
            temples.add(new Place("Hanuman Temple", "First floor, PPPP+9PW Hanumant Dham, Sector 40B, Chandigarh, 160036", "6AM to 7:30PM"));
            //  ArrayList<Place> temples = new ArrayList<>();
            temples.add(new Place("Prachin Shiv Mandir", " PQFG+FF5, Sarovar Path, Sector 21C, Sector 21, Chandigarh, 160022", "4AM to 9:00PM"));
            // ArrayList<Place> temples = new ArrayList<>();
            temples.add(new Place("Shri Krishan Mandir", "PQ79+4R8, D, Sector 33C, Sector 33, Chandigarh, 160033", "9AM to 10:00PM"));
            // ArrayList<Place> temples = new ArrayList<>();


            // Adding details for Tourist Places
            ArrayList<Place> touristPlaces = new ArrayList<>();
            touristPlaces.add(new Place(" Japanese Garden", "PQ3M+RP6, near Japanese Park, Sector 31 A, Sector 31, Chandigarh, 160030", 20));
            //ArrayList<Place> touristPlaces = new ArrayList<>();
            touristPlaces.add(new Place("Rock Garden", " QR34+52F, Uttar Marg, Rock Garden of Chandigarh, Sector 1, Chandigarh, 160001", 100));
            // ArrayList<Place> touristPlaces = new ArrayList<>();
            touristPlaces.add(new Place("Chandigarh Bird Park", "Infront of the Lake Sports Club entry,Sector 1, Chandigarh, 160001", 50));
            //ArrayList<Place> touristPlaces = new ArrayList<>();
            touristPlaces.add(new Place("Government Museum and Art Gallery", " Sector, 10C, Sector 10, Chandigarh, 160011",  20));
            //ArrayList<Place> touristPlaces = new ArrayList<>();

            smartCityProject.places.put("Hotels", hotels);
            smartCityProject.places.put("Restaurants", restaurants);
            smartCityProject.places.put("Temples", temples);
            smartCityProject.places.put("Tourist Places", touristPlaces);
        }
    }

