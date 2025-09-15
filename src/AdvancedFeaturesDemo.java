import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

    public class AdvancedFeaturesDemo {

        private final double globalDiscountRate = 0.15;

        // 特征点：这是一个“Record”类 (Java 16+)，反编译后会展开成一个完整的final class
        public record Product(String id, String name, double price, String status) {}

        // 特征点：这是一个泛型接口
        interface DataProcessor<T> {
            void processData(T data);
            String getReport();
        }

        static class ProductProcessor implements DataProcessor<Product> {
            private final StringBuilder report = new StringBuilder();

            @Override
            public void processData(Product product) {
                String productInfo = "产品ID: " + product.id() + ", 名称: " + product.name(); // 特征点：字符串拼接

                switch (product.status()) {
                    case "PENDING":
                        report.append(productInfo).append(" - 状态：待处理\n");
                        break;
                    case "SHIPPED":
                        report.append(productInfo).append(" - 状态：已发货\n");
                        break;
                    default:
                        report.append(productInfo).append(" - 状态：未知\n");
                        break;
                }
            }

            @Override
            public String getReport() {
                return report.toString();
            }
        }

        /**
         * 特征点：这是一个内部类，它会访问外部类的私有成员
         */
        class DiscountCalculator {
            public double getDiscountedPrice(Product p) {
                // 这里访问了外部类的私有成员 globalDiscountRate
                return p.price() * (1 - globalDiscountRate);
            }
        }

        public static void main(String[] args) {
            AdvancedFeaturesDemo demo = new AdvancedFeaturesDemo();

            // 特征点：使用了泛型 (Generics)
            List<Product> productList = new ArrayList<>();
            productList.add(new Product("A-001", "笔记本电脑", 8000.0, "SHIPPED"));
            productList.add(new Product("B-002", "机械键盘", 500.0, "PENDING"));

            ProductProcessor processor = new ProductProcessor();

            // 特征点：使用了增强型for循环 (for-each)
            for (Product product : productList) {
                processor.processData(product);
            }

            System.out.println("--- 处理报告 ---");
            System.out.println(processor.getReport());

            // 演示内部类的使用
            DiscountCalculator calculator = demo.new DiscountCalculator();
            Product firstProduct = productList.get(0);
            double discountedPrice = calculator.getDiscountedPrice(firstProduct);
            System.out.println("--- 折扣计算 ---");
            System.out.println(firstProduct.name() + " 的折后价是: " + discountedPrice);
        }
    }

