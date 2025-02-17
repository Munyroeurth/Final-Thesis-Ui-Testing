package org.example.nlptest;

import org.openqa.selenium.By;

public class NLPToLocator {
    public static By createLocator(String element, String text, String location) {
        // Khởi tạo XPath cơ bản
        String xpath = "";

        // Xác định loại phần tử (element)
        switch (element.toLowerCase()) {
            case "nút":
                xpath = "//*";
                break;
            case "hộp văn bản":
                xpath = "//input";
                break;
            default:
                xpath = "//*"; // Nếu không xác định, chọn bất kỳ phần tử nào
        }

        // Thêm điều kiện dựa trên text
        if (!text.isEmpty()) {
            xpath += "[text()='" + text + "']";
        }

        // Xử lý vị trí (location)
        if (location.contains("góc phải")) {
            // Thêm điều kiện dựa trên CSS hoặc thuộc tính
            xpath += "[contains(@style,'float: right')]";
        }

        // Trả về đối tượng định vị
        return By.xpath(xpath);
    }

    public static void main(String[] args) {
        // Dữ liệu từ NLP
        String element = "nút";
        String text = "Đăng nhập";
        String location = "góc phải";

        // Chuyển đổi thành XPath
        By locator = createLocator(element, text, location);
        System.out.println("XPath: " + locator.toString());

        // Kết quả: XPath: //button[text()='Đăng nhập'][contains(@style,'float: right')]
    }
}
