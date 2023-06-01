با عرض سلام و خسته نباشید خدمت شما

## مقدمه
پروژه با زبان Kotlin و فریم‌ورک spring boot  و طبق معماری Clean Architecture توسعه داده شده است. پیاده سازی شامل دو ماژول domain و application می‌باشد.

ماژول domain حاوی پیاده سازی business logic می‌باشد و به هیچ ابزار و library وابستگی ندارد. اما ماژول application با استفاده از spring boot پیاده سازی شده است و تمامی سرویس های سطح پایین مثل ارتباط با پایگاه‌داده و سایر سرویس ها و تکنولوژی ها در این ماژول قرار خواهند گرفت.

## اجرای پروژه
برای اجرای پروژه به وسیله `docker-compose` می‌توانید به شکل زیر عمل نمایید (برای تکمیل build پروژه نیاز به VPN خواهید داشت، متاسفانه بعضی از وابستگی‌های maven تحریم هستند).
```
docker-compose up -d
```

پس از اجرای موفقیت آمیز پروژه، api زیر را فراخوانی نموده تا مقداری داده تستی درون جداول قرار گیرد
```
curl 'http://localhost:8080/demo/fill'
```

## لیست API ها
- گزارش تاخیر
```
curl --request POST 'http://localhost:8080/api/v1.0/orders/{orderId}/delay'
```
- اختصاص یافتن به agent
```
- curl --request POST 'http://localhost:8080/agent/api/v1.0/orders/delays/assign?agentId={agentId}
```
- گزارش تاخیر 7 روز گذشته
```
curl 'http://localhost:8080/admin/api/v1.0/reports/vendors/delays'
```

### کلام آخر
پیاده سازی دارای نقص‌هایی هم هست که به علت کمبود وقت فرصت مرتفع کردن و بهبود دادنش نشد. و ممنون از این فرصتی که در اختیار بنده قرار دادید.