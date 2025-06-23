# 🧠 ThinkSync - Personalized Note Taker

ThinkSync is a powerful and user-friendly web application that allows users to seamlessly create, manage, filter, and share categorized notes. 
Designed for productivity and accessibility, ThinkSync enables users to organize thoughts into categories, download notes in multiple formats, and even share via email or QR code.

---

## 🚀 Features

### 📝 Notes & Categories
- Create an account and log in securely.
- Add, edit, and delete **categories** and **notes**.
- Associate multiple notes with respective categories for better organization.

### 🔍 Filter & Search
- Easily filter notes by **category**, **title**, or **date**.
- Search and sort to quickly find relevant content.

### 📥 Download Options
- Download any note as:
  - `.txt`
  - `.pdf`
  - `.docx`

### 📱 QR Code Sharing
- Each note is associated with a **unique QR code**.
- Scan the code with your mobile, tablet, or any other device to instantly **download the note**.

### 📧 Email Sharing
- Share notes directly with your friends via email.
- Choose the file format and customize the subject and body of the email.

### 💬 User Feedback
- Users can leave **feedback** to share thoughts, improvements, or report bugs.

---

## 🔐 User Access

- Users must create an account to start creating and managing notes.
- Secure login and session management.

---

## 💡 Use Cases

- **Students** organizing lecture notes into categories.
- **Writers** drafting and exporting content.
- **Professionals** managing tasks or meeting notes.
- **Teachers** sharing study material via QR codes or emails.

---

## 🛠️ Technologies Used

- **Frontend**: HTML, CSS, JavaScript, Bootstrap
- **Backend**: Java (Servlets, JSP), Hibernate
- **Database**: MySQL
- **PDF/Doc Generation**: iText, Apache POI
- **QR Code**: ZXing Library
- **Email Service**: JavaMail API

---

## 📂 Project Structure

thinksync/
├── src/
│ ├── controllers/
│ ├── models/
│ ├── services/
│ ├── utils/
│ └── views/
├── webapp/
│ ├── pages/
│ ├── css/
│ ├── js/
│ └── WEB-INF/
├── pom.xml
└── README.md
