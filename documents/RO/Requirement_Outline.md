# Requirement Outline - Ứng dụng Quản lý Đồ án Công nghệ Phần mềm

## Tổng quan
- Môn đồ án Công nghệ Phần mềm này, mỗi năm có nhiều lớp, mỗi lớp có nhiều nhóm (tối đa 6 sinh viên/nhóm).

## 1. Quản lý sinh viên và nhóm
- Đăng ký tài khoản sinh viên (qua email HUSC)
- Đăng nhập bằng tài khoản HUSC
- Đăng ký nhóm (tối đa 6 sinh viên)
- Hiển thị thông tin nhóm (thành viên, đề tài, giảng viên hướng dẫn)
- Hiển thị danh sách sinh viên đã có nhóm và chưa có nhóm
- Mời sinh viên vào nhóm
- Giảng viên quản lý thông tin sinh viên (xem danh sách, thông tin cá nhân)
- Sinh viên chỉnh sửa thông tin cá nhân
- Tự động tạo nhóm cho sinh viên chưa có nhóm khi đến hạn chót đăng ký nhóm, ưu tiên sinh viên cùng lớp học phần, nếu thiếu sẽ lấy sinh viên từ các lớp khác.

### 1.1. Đăng ký thông tin cá nhân của sinh viên
- **Mục đích:** Cho phép sinh viên cung cấp và lưu trữ thông tin cá nhân cần thiết cho việc quản lý đồ án.
- **Các bước thực hiện:**
  - Sinh viên truy cập trang đăng ký thông tin cá nhân.
  - Sinh viên nhập các thông tin như mã sinh viên, họ tên, số điện thoại, email, khóa, ngành học.
  - Hệ thống kiểm tra tính hợp lệ của dữ liệu (ví dụ: định dạng email, độ dài số điện thoại).
  - Hệ thống lưu trữ thông tin vào cơ sở dữ liệu.
- **Dữ liệu đầu vào:** Mã sinh viên, họ tên, số điện thoại, email, khóa, ngành học.
- **Dữ liệu đầu ra:** Thông báo đăng ký thành công/thất bại.
- **Trường hợp đặc biệt/ngoại lệ:**
  - Sinh viên nhập thiếu hoặc sai định dạng thông tin.
  - Mã sinh viên đã tồn tại trong hệ thống.
- **Ràng buộc:**
  - Mã sinh viên là duy nhất.
  - Các trường thông tin bắt buộc phải được điền đầy đủ.

### 1.2. Đăng nhập bằng tài khoản HUSC
- **Mục đích:** Xác thực sinh viên thông qua tài khoản HUSC để đảm bảo tính bảo mật và tự động điền thông tin cá nhân.
- **Các bước thực hiện:**
  - Sinh viên truy cập trang đăng nhập của ứng dụng.
  - Sinh viên được chuyển hướng đến trang đăng nhập của HUSC.
  - Sinh viên nhập tài khoản và mật khẩu HUSC.
  - Hệ thống HUSC xác thực thông tin.
  - Sau khi xác thực thành công, hệ thống ứng dụng nhận thông tin từ HUSC (mã sinh viên, họ tên, email, v.v.).
  - Hệ thống ứng dụng tự động điền các thông tin này vào hồ sơ cá nhân của sinh viên (nếu chưa có) hoặc cập nhật (nếu đã có).
- **Dữ liệu đầu vào:** Tài khoản HUSC, mật khẩu HUSC.
- **Dữ liệu đầu ra:** Thông tin cá nhân của sinh viên từ tài khoản HUSC.
- **Trường hợp đặc biệt/ngoại lệ:**
  - Đăng nhập HUSC thất bại (sai tài khoản/mật khẩu).
  - Lỗi kết nối với hệ thống HUSC.
- **Ràng buộc:** Yêu cầu tích hợp với hệ thống xác thực của HUSC.

### 1.3. Đăng ký nhóm
- **Mục đích:** Cho phép sinh viên tạo hoặc tham gia nhóm để thực hiện đồ án.
- **Các bước thực hiện:**
  - Sinh viên (chưa có nhóm) truy cập trang đăng ký nhóm.
  - Sinh viên có thể chọn tạo nhóm mới hoặc tham gia nhóm đã có.
  - **Tạo nhóm mới:**
    - Sinh viên nhập tên nhóm.
    - Sinh viên mời các thành viên khác (bằng mã sinh viên hoặc email).
    - Hệ thống kiểm tra điều kiện (ví dụ: số lượng thành viên, ưu tiên sinh viên cùng nhóm học phần).
    - Hệ thống tạo nhóm và cập nhật trạng thái thành viên.
  - **Tham gia nhóm:**
    - Sinh viên tìm kiếm nhóm theo tên hoặc mã nhóm.
    - Sinh viên gửi yêu cầu tham gia nhóm.
    - Nhóm trưởng nhận được yêu cầu và duyệt/từ chối.
    - Hệ thống cập nhật trạng thái thành viên sau khi nhóm trưởng duyệt.
- **Dữ liệu đầu vào:** Tên nhóm, danh sách thành viên mời, mã nhóm, yêu cầu tham gia.
- **Dữ liệu đầu ra:** Thông báo tạo nhóm thành công, thông báo tham gia nhóm, cập nhật danh sách thành viên.
- **Trường hợp đặc biệt/ngoại lệ:**
  - Tên nhóm trùng lặp.
  - Số lượng thành viên vượt quá giới hạn.
  - Sinh viên đã có nhóm.
  - Sinh viên không cùng nhóm học phần (nếu có ràng buộc).
- **Ràng buộc:**
  - Mỗi sinh viên chỉ thuộc một nhóm.
  - Giới hạn số lượng thành viên trong một nhóm (tối đa 6 sinh viên).
  - Ưu tiên sinh viên cùng nhóm học phần (nếu có).

### 1.4. Hiển thị thông tin nhóm
- **Mục đích:** Cung cấp cái nhìn tổng quan về các nhóm và thành viên cho tất cả người dùng.
- **Các bước thực hiện:**
  - Người dùng (sinh viên, giảng viên, quản trị viên) truy cập trang danh sách nhóm.
  - Hệ thống hiển thị danh sách các nhóm.
  - Khi chọn một nhóm, hệ thống hiển thị chi tiết thông tin nhóm (tên nhóm, danh sách thành viên).
- **Dữ liệu đầu vào:** Yêu cầu xem danh sách nhóm, chọn một nhóm cụ thể.
- **Dữ liệu đầu ra:** Danh sách nhóm, thông tin chi tiết của nhóm (tên, thành viên).
- **Trường hợp đặc biệt/ngoại lệ:** Không tìm thấy nhóm.
- **Ràng buộc:** Dữ liệu phải được cập nhật theo thời gian thực.

### 1.5.- Hiển thị danh sách sinh viên đã có nhóm và chưa có nhóm.
- **Đến hạn đăng ký, hệ thống tự động lập nhóm cho các sinh viên chưa có nhóm, ưu tiên sinh viên cùng nhóm lớp học phần. Nếu lớp hết người nhưng nhóm vẫn chưa đủ, gom từ các nhóm khác.** 
- **Mục đích:** Giúp giảng viên và quản trị viên dễ dàng theo dõi tình trạng nhóm của sinh viên.
- **Các bước thực hiện:**
  - Giảng viên/Quản trị viên truy cập trang quản lý sinh viên/nhóm.
  - Hệ thống hiển thị hai danh sách riêng biệt: sinh viên đã có nhóm và sinh viên chưa có nhóm.
  - Thông tin hiển thị bao gồm: mã sinh viên, họ tên, và tên nhóm (nếu đã có nhóm).
- **Dữ liệu đầu vào:** Yêu cầu xem danh sách sinh viên.
- **Dữ liệu đầu ra:** Danh sách sinh viên đã có nhóm, danh sách sinh viên chưa có nhóm.
- **Trường hợp đặc biệt/ngoại lệ:** Không có sinh viên nào trong danh sách.
- **Ràng buộc:** Dữ liệu phải được cập nhật tự động khi sinh viên tạo/tham gia nhóm.

### 1.6. Cho phép sinh viên gửi lời mời tham gia nhóm
- **Mục đích:** Tạo điều kiện cho sinh viên chủ động tìm kiếm và mời thành viên vào nhóm.
- **Các bước thực hiện:**
  - Sinh viên (nhóm trưởng) truy cập trang quản lý nhóm.
  - Sinh viên chọn chức năng "Mời thành viên".
  - Sinh viên nhập mã sinh viên hoặc email của người muốn mời.
  - Hệ thống gửi lời mời đến sinh viên được mời.
  - Sinh viên được mời nhận được thông báo và có thể chấp nhận/từ chối lời mời.
  - Hệ thống cập nhật trạng thái nhóm sau khi lời mời được chấp nhận.
- **Dữ liệu đầu vào:** Mã sinh viên/email của người được mời, chấp nhận/từ chối lời mời.
- **Dữ liệu đầu ra:** Lời mời tham gia nhóm, thông báo chấp nhận/từ chối.
- **Trường hợp đặc biệt/ngoại lệ:**
  - Sinh viên được mời đã có nhóm.
  - Sinh viên được mời không tồn tại trong hệ thống.
  - Lời mời hết hạn.
- **Ràng buộc:** Lời mời có thời hạn, số lượng lời mời tối đa.

### 1.7. Quản lý thông tin giảng viên hướng dẫn
- **Mục đích:** Lưu trữ và quản lý thông tin của các giảng viên hướng dẫn đồ án.
- **Các bước thực hiện:**
  - Quản trị viên truy cập trang quản lý giảng viên.
  - Quản trị viên có thể thêm mới, sửa, xóa thông tin giảng viên.
  - Thông tin bao gồm: tên giảng viên, email, số lượng nhóm có thể hướng dẫn.
  - Hệ thống kiểm tra tính hợp lệ của dữ liệu.
  - Hệ thống lưu trữ/cập nhật thông tin giảng viên.
- **Dữ liệu đầu vào:** Tên giảng viên, email, số lượng nhóm hướng dẫn.
- **Dữ liệu đầu ra:** Thông báo thêm/sửa/xóa thành công, danh sách giảng viên.
- **Trường hợp đặc biệt/ngoại lệ:**
  - Email giảng viên trùng lặp.
  - Xóa giảng viên đang hướng dẫn nhóm.
- **Ràng buộc:** Email giảng viên là duy nhất.

## 2. Quản lý đề tài đồ án

### 2.1. Danh sách các đề tài tham khảo
- **Mục đích:** Cung cấp cho sinh viên và giảng viên một danh sách các đề tài gợi ý để tham khảo hoặc lựa chọn.
- **Các bước thực hiện:**
  - Người dùng truy cập trang danh sách đề tài tham khảo.
  - Hệ thống hiển thị danh sách các đề tài, bao gồm tên đề tài, mô tả ngắn gọn, và các thông tin liên quan (nếu có).
  - Người dùng có thể tìm kiếm, lọc các đề tài theo tiêu chí nhất định.
- **Dữ liệu đầu vào:** Yêu cầu xem danh sách đề tài, tiêu chí tìm kiếm/lọc.
- **Dữ liệu đầu ra:** Danh sách các đề tài tham khảo.
- **Trường hợp đặc biệt/ngoại lệ:** Không có đề tài nào trong danh sách.
- **Ràng buộc:** Dữ liệu đề tài phải được quản lý và cập nhật bởi quản trị viên.

### 2.2. Sinh viên/nhóm đăng ký đề tài
- **Mục đích:** Cho phép sinh viên hoặc nhóm đăng ký một đề tài để thực hiện đồ án.
- **Các bước thực hiện:**
  - Sinh viên/nhóm trưởng truy cập trang đăng ký đề tài.
  - Sinh viên/nhóm trưởng có thể chọn một đề tài từ danh sách tham khảo hoặc đề xuất một đề tài mới.
  - **Chọn đề tài từ danh sách:**
    - Sinh viên/nhóm trưởng chọn đề tài mong muốn.
    - Hệ thống kiểm tra tính khả dụng của đề tài (đã có nhóm nào đăng ký chưa).
    - Nếu đề tài khả dụng, hệ thống ghi nhận đăng ký.
  - **Đề xuất đề tài mới:**
    - Sinh viên/nhóm trưởng nhập tên đề tài, mô tả chi tiết, mục tiêu, công nghệ dự kiến.
    - Hệ thống gửi đề xuất đến giảng viên/quản trị viên để duyệt.
    - Giảng viên/quản trị viên duyệt/từ chối đề xuất.
- **Dữ liệu đầu vào:** Tên đề tài, mô tả, mục tiêu, công nghệ dự kiến, lựa chọn đề tài từ danh sách.
- **Dữ liệu đầu ra:** Thông báo đăng ký thành công/thất bại, trạng thái đề xuất (đang chờ duyệt, đã duyệt, từ chối).
- **Trường hợp đặc biệt/ngoại lệ:**
  - Đề tài đã có nhóm đăng ký.
  - Đề xuất đề tài mới không được duyệt.
- **Ràng buộc:**
  - Mỗi nhóm chỉ được đăng ký một đề tài.
  - Đề tài phải được duyệt trước khi nhóm bắt đầu thực hiện.

### 2.3. Kiểm tra đề tài trùng lặp
- **Mục đích:** Đảm bảo mỗi đề tài là duy nhất và tránh việc nhiều nhóm cùng làm một đề tài.
- **Các bước thực hiện:**
  - Khi sinh viên/nhóm đăng ký hoặc đề xuất đề tài, hệ thống tự động kiểm tra trùng lặp.
  - Hệ thống so sánh tên đề tài và/hoặc mô tả với các đề tài đã có trong cơ sở dữ liệu.
  - Nếu phát hiện trùng lặp, hệ thống thông báo cho người dùng.
- **Dữ liệu đầu vào:** Tên đề tài, mô tả đề tài.
- **Dữ liệu đầu ra:** Thông báo trùng lặp hoặc xác nhận đề tài duy nhất.
- **Trường hợp đặc biệt/ngoại lệ:** Phát hiện trùng lặp nhưng người dùng vẫn muốn tiếp tục (cần sự can thiệp của giảng viên/quản trị viên).
- Ràng buộc: Thuật toán kiểm tra trùng lặp cần đủ mạnh để phát hiện các đề tài tương tự.

## 3. Quản lý tiến độ và báo cáo

### 3.1. Nhóm trưởng gửi email báo cáo kết quả công việc hàng tuần
- **Mục đích:** Cung cấp cơ chế để nhóm trưởng báo cáo tiến độ công việc định kỳ cho giảng viên hướng dẫn.
- **Các bước thực hiện:**
  - Nhóm trưởng truy cập chức năng gửi báo cáo.
  - Nhóm trưởng điền thông tin báo cáo (kết quả công việc, khó khăn, kế hoạch tuần tới).
  - Hệ thống gửi báo cáo qua email đến giảng viên hướng dẫn.
  - Hệ thống lưu trữ báo cáo trong cơ sở dữ liệu.
- **Dữ liệu đầu vào:** Nội dung báo cáo (văn bản, file đính kèm).
- **Dữ liệu đầu ra:** Thông báo gửi báo cáo thành công, email báo cáo gửi đến giảng viên.
- **Trường hợp đặc biệt/ngoại lệ:**
  - Lỗi gửi email.
  - Nhóm trưởng quên gửi báo cáo (cần nhắc nhở).
- **Ràng buộc:**
  - Báo cáo phải được gửi đúng hạn (ví dụ: cuối mỗi tuần).
  - Nội dung báo cáo phải tuân thủ định dạng nhất định (nếu có).

### 3.2. Giảng viên/chuyên gia doanh nghiệp gửi phản hồi về kết quả công việc
- **Mục đích:** Cho phép giảng viên và chuyên gia doanh nghiệp cung cấp phản hồi về tiến độ và chất lượng công việc của nhóm.
- **Các bước thực hiện:**
  - Giảng viên/chuyên gia truy cập báo cáo của nhóm.
  - Giảng viên/chuyên gia điền phản hồi (nhận xét, đánh giá, góp ý).
  - Hệ thống lưu trữ phản hồi và thông báo cho nhóm trưởng.
- **Dữ liệu đầu vào:** Nội dung phản hồi.
- **Dữ liệu đầu ra:** Thông báo phản hồi thành công, phản hồi hiển thị cho nhóm trưởng.
- **Trường hợp đặc biệt/ngoại lệ:** Lỗi khi lưu phản hồi.
- **Ràng buộc:** Phản hồi phải được gửi trong thời gian quy định.

### 3.3. Kế hoạch làm việc hàng tuần (có thể tích hợp hoặc tham chiếu đến Google Sheet)
- **Mục đích:** Giúp nhóm lập kế hoạch công việc chi tiết và theo dõi tiến độ.
- **Các bước thực hiện:**
  - Nhóm trưởng/thành viên truy cập chức năng lập kế hoạch.
  - Nhóm có thể tạo kế hoạch trực tiếp trong ứng dụng hoặc liên kết đến Google Sheet.
  - **Tạo kế hoạch trong ứng dụng:**
    - Nhóm điền các công việc, người phụ trách, thời hạn, trạng thái.
    - Hệ thống hiển thị kế hoạch và cho phép cập nhật trạng thái công việc.
  - **Tham chiếu Google Sheet:**
    - Nhóm cung cấp đường dẫn Google Sheet chứa kế hoạch.
    - Hệ thống hiển thị đường dẫn hoặc nhúng nội dung Google Sheet (nếu có thể).
- **Dữ liệu đầu vào:** Công việc, người phụ trách, thời hạn, trạng thái, đường dẫn Google Sheet.
- **Dữ liệu đầu ra:** Kế hoạch làm việc hiển thị, trạng thái công việc được cập nhật.
- **Trường hợp đặc biệt/ngoại lệ:**
  - Lỗi khi tích hợp/nhúng Google Sheet.
  - Kế hoạch không được cập nhật thường xuyên.
- Ràng buộc: Kế hoạch phải rõ ràng, có thể theo dõi được.

## 4. Quản lý chấm điểm và bảo vệ đồ án
- Giảng viên nhập điểm cho từng sinh viên/nhóm
- Sinh viên xem điểm
- Quản lý lịch bảo vệ đồ án (thời gian, địa điểm, hội đồng)

## 5. Quản lý thông báo và nhắc nhở
- Giảng viên tạo thông báo chung
- Giảng viên tạo ngày deadline
- Gửi thông báo chung từ giảng viên đến sinh viên
- Gửi nhắc nhở tự động (hạn nộp báo cáo, hạn đăng ký nhóm, hạn bảo vệ)

## 6. Quản lý quyền truy cập (RBAC)
- Sinh viên: chỉ xem thông tin của mình, nhóm, đề tài, nộp báo cáo
- Giảng viên: quản lý sinh viên, nhóm, đề tài, xem báo cáo, nhập điểm, tạo thông báo, tạo deadline
- Quản trị viên: quản lý người dùng, phân quyền, cấu hình hệ thống

## 7. Quản lý đề tài
- Giảng viên có thể yêu cầu đổi đề tài

## Công nghệ sử dụng
- Backend: Spring Boot
- Frontend: Tailwind CSS

### 4.1. Thông tin về hình thức thi kết thúc học phần
- **Mục đích:** Cung cấp thông tin rõ ràng về hình thức thi để sinh viên nắm rõ và chuẩn bị.
- **Các bước thực hiện:**
  - Giảng viên/Quản trị viên nhập thông tin về hình thức thi (ví dụ: thi trên máy tính, thi vấn đáp, thi viết).
  - Hệ thống hiển thị thông tin này cho sinh viên.
- **Dữ liệu đầu vào:** Mô tả hình thức thi.
- **Dữ liệu đầu ra:** Thông tin hình thức thi hiển thị cho sinh viên.
- **Trường hợp đặc biệt/ngoại lệ:** Thông tin không được cập nhật kịp thời.
- **Ràng buộc:** Thông tin phải chính xác và dễ hiểu.

### 4.2. Quản lý điểm quá trình học tập
- **Mục đích:** Ghi nhận và quản lý điểm quá trình của sinh viên dựa trên kết quả thực hiện đồ án.
- **Các bước thực hiện:**
  - Giảng viên nhập điểm quá trình cho từng nhóm/sinh viên.
  - Điểm có thể bao gồm các thành phần như điểm báo cáo tuần, điểm sản phẩm, điểm thái độ.
  - Hệ thống tính toán điểm tổng kết quá trình.
  - Hệ thống hiển thị điểm quá trình cho sinh viên và giảng viên.
- **Dữ liệu đầu vào:** Điểm thành phần (báo cáo, sản phẩm, thái độ).
- **Dữ liệu đầu ra:** Điểm quá trình tổng kết, bảng điểm.
- **Trường hợp đặc biệt/ngoại lệ:**
  - Nhập sai điểm.
  - Lỗi tính toán điểm.
- **Ràng buộc:**
  - Điểm phải được nhập đúng thời hạn.
  - Công thức tính điểm phải rõ ràng và minh bạch.