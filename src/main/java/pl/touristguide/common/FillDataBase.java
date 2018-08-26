package pl.touristguide.common;

import pl.touristguide.model.Account;
import pl.touristguide.model.Category;
import pl.touristguide.model.Place;
import pl.touristguide.model.UserDetail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FillDataBase {

    public static List<Category> categoryTestData() throws Exception {

        List<Category> categories = new ArrayList<>();
        categories.add(addCategory("ZABYTKI"));
        categories.add(addCategory("MUZEA"));
        categories.add(addCategory("TRANSPORT"));
        categories.add(addCategory("ROZRYWKA"));
        categories.add(addCategory("NOCLEG"));
        categories.add(addCategory("PRZYRODA"));
        categories.add(addCategory("RESTAURACJE"));
        categories.add(addCategory("SPORT I REKREACJA"));
        categories.add(addCategory("NOCNE ŻYCIE"));
        categories.add(addCategory("WYDARZENIA"));
        categories.add(addCategory("ZAKUPY"));
        categories.add(addCategory("BEZPIECZEŃSTWO"));
        return categories;
    }

    public static List<Place> placeTestData(List<Category> categories, Account account) throws Exception {
        List<Place> places = new ArrayList<>();
        places.add(addPlace("Pałacyk Zielińskiego", "Pałacyk Zielińskiego.png","Jeden z najcenniejszych zabytków architektury Kielc, znajduje się w centrum miasta przy ulicy Zamkowej, granicząc z innymi zabytkami: Parkiem Miejskim i byłym więzieniem kieleckim. Ulica Zamkowa ciągnęła się dawniej aż do ulicy Chęcińskiej. Dalej w stronę Krakowa wiódł tędy stary trakt zwany 'Starą Drogą'. Zabudowę ulicy stanowiły budynki przypałacowe zaczynające się od południowego skrzydła pałacu i XVII-wiecznej bramy aż do stawu. Były tu stajnie, wozownie, budynek starosty, spichlerz, mieszkanie dla pisarza prewentowego i dla praczki oraz tzw. rajszula, czyli ujeżdżalnia koni (szkoła końska) wybudowana w 1752 r. przez biskupa Andrzeja Załuskiego. To właśnie na jej fundamentach wzniesiono w latach 1847-1857 zabudowania pałacyku Tomasza Zielińskiego.",
                categories.get(0), new BigDecimal(50.8660253818053), new BigDecimal(20.6285212025977), "http://www.palacykzielinskiego.pl", account));
        places.add(addPlace("Echo", "Echo.png", "Największa galeria handlowa w Polsce pod względem liczby sklepów. Została otwarta 30 listopada 2002 roku. W latach 2009–2011 dokonano przebudowy według projektu pracowni architektonicznej Detan z Kielc, natomiast projekt elewacji i wnętrz wykonała warszawska pracownia architektoniczna Open Architekci. W 2011 roku Galeria Echo zwyciężyła w kategorii Best enlarged retail development (dla najlepszego rozbudowanego obiektu handlowego) w międzynarodowym konkursie MAPIC Awards. W Galerii Echo znajduje się około 300 sklepów i punktów usługowych. Centrum składa się z czterech poziomów (-1, 0, 1 i 2) – na najwyższym poziomie znajdują się: kręgielnia, siłownia, fitness, bawialnia dla dzieci, centrum medyczne, salony Komfort. Powierzchnia całkowita to 159 tys. m², z czego 70 tys. m² stanowi powierzchnia najmu",
                categories.get(10), new BigDecimal(50.8807268310492), new BigDecimal(20.6465721130371), "http://www.galeriaecho.pl", account));
        places.add(addPlace("Targi kielce", "Targi kielce.png", "Polski ośrodek wystawienniczy. Wicelider targów w Polsce, członek UFI – Światowego związku Przemysłu Targowego, CENTREX – Międzynarodowego Związku Statystyk Targowych i do stycznia 2012 roku Polskiej Izby Przemysłu Targowego. Druga, co do wielkości, własna powierzchnia wystawowa w kraju. Klimatyzowane pawilony wystawowe z pełną infrastrukturą techniczną, nowoczesne centrum konferencyjne. Jedyne targi ze specjalnym terenem pokazowym do dynamicznej prezentacji każdego rodzaju sprzętu. Organizator specjalistycznych targów branżowych i wystaw gospodarczych oraz towarzyszących im konferencji, seminariów i sympozjów.",
                categories.get(9), new BigDecimal(50.8985400058829), new BigDecimal(20.5866622924805), "http://www.targikielce.pl", account));
        return places;
    }

    public static UserDetail userDetailTestData() throws Exception {
        return addUserDetail("admin", "admin@admin.com");
    }

    public static Account accountTestData(UserDetail userDetail) throws Exception {
        return addAccount("admin", "admin", userDetail);
    }

    private static Category addCategory(String name) throws Exception {
        Category category = new Category();
        category.setName(name);
        return category;
    }

    private static Place addPlace(String name, String image, String description, Category category, BigDecimal lat,
                                  BigDecimal lng, String websiteLink, Account account) throws Exception {
        Place place = new Place();
        place.setName(name);
        place.setDescription(description);
        place.setCategory(category);
        place.setImageName(image);
        place.setLatitude(lat);
        place.setLongitude(lng);
        place.setWebsiteLink(websiteLink);
        place.setAccount(account);
        return place;
    }

    private static UserDetail addUserDetail(String name, String email) throws Exception  {
        UserDetail userDetail = new UserDetail();
        userDetail.setName(name);
        userDetail.setEmail(email);
        return userDetail;
    }

    private static Account addAccount(String login, String password, UserDetail userDetail) throws Exception {
        Account account = new Account();
        account.setLogin(login);
        account.setPassword(HashUtils.generateHash(password,  10));
        account.setUserDetail(userDetail);
        return account;
    }
}
