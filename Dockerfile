# المرحلة الأولى: بناء التطبيق باستخدام Maven و OpenJDK 23
# نستخدم صورة OpenJDK 23 مع Maven.
# ملاحظة: قد لا تكون "maven:3.9.6-openjdk-23" متاحة بعد على Docker Hub
# إذا واجهت مشاكل في البناء، قد تحتاج للبحث عن أحدث صورة Maven متوافقة مع JDK 23
# أو استخدام البديل الذي يقوم بتثبيت Maven يدوياً (كما هو موضح في الشرح).
FROM maven:3.9.6-openjdk-23 AS build # هذا افتراض لأحدث نسخة، تحقق من Docker Hub إذا واجهت مشاكل

# تعيين دليل العمل داخل حاوية البناء
WORKDIR /app

# نسخ ملف pom.xml أولاً للاستفادة من طبقات Docker cache
COPY pom.xml .

# نسخ مجلد src (الذي يحتوي على كود المصدر)
COPY src ./src

# تشغيل أمر البناء باستخدام Maven.
RUN mvn clean package -DskipTests

# -------------------------------------------------------------
# المرحلة الثانية: تشغيل التطبيق في بيئة JRE 23 أصغر
FROM openjdk:23-jre-slim

# تعيين دليل العمل داخل حاوية التشغيل
WORKDIR /app

# نسخ ملف JAR الناتج من مرحلة البناء إلى حاوية التشغيل
# **هذا هو السطر الذي تم تحديثه باسم ملف JAR الصحيح.**
COPY --from=build /app/target/converter-0.0.1-SNAPSHOT.jar app.jar

# تحديد المنفذ الذي سيستمع إليه التطبيق
EXPOSE 8080

# أمر تشغيل التطبيق
ENTRYPOINT ["java", "-jar", "app.jar"]
