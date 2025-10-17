# TermiClock

A simple clock for the terminal, written in Java. Display the time and date in real-time with multiple font designs directly from your terminal or via a Windows executable.

---

## ✨ Features

- ⏰ Real-time clock display
- 🎨 Multiple fonts available: `hashtag`, `star`, `clock`
- 📅 Optional date display
- 💾 Compatible with `.jar` and `.exe` (Windows)
- 🖥️ Minimalist and readable terminal interface

---

## 📋 Prerequisites

- **Java 17** or higher
- **Maven** or **IntelliJ** to compile from source
- *(Optional)* **Launch4j** or **jpackage** to generate a `.exe`

---

## 📁 Project Structure

```
termiclock/
├── src/
│   └── main/
│       ├── java/org/termiclock/
│       │   ├── Main.java
│       │   ├── clock/
│       │   │   └── ClockProcess.java
│       │   └── util/
│       │       └── JsonParser.java
│       └── resources/
│           └── font/
│               ├── types.json
│               ├── data/           # Digits for each font
│               └── dots/           # Separator characters
├── out/                            # Build folder
├── termiclock.jar
├── termiclock.exe
└── README.md
```

JSON files in `resources/font/` define the fonts and special characters for the clock.

---

## 🔨 Compilation

### Using IntelliJ

1. Ensure the `font/` folder is in `src/main/resources`
2. Go to **Build** → **Build Artifacts** → Select your artifact
3. Configure it to include resources
4. Click **Build**
5. Test the generated JAR:
   ```bash
   java -jar TermiClock.jar -h
   ```

### Using Command Line

```bash
# Compile the project
javac -d out -cp "src/main/java" $(find src/main/java -name "*.java")

# Copy resources
cp -r src/main/resources/* out/

# Create the JAR
jar --create --file TermiClock.jar --main-class org.termiclock.Main -C out .
```

---

## 🚀 Usage

### With the Windows Executable (Portable / zip)
0. Get the zip file at  [this link](https://drive.google.com/file/d/1MDriizUK-VTr-l-YGkDKGT-HrCqqU-mS/view?usp=sharing)
1. Extract the zip folder containing `termiclock.exe` and the `jre/` folder.
2. **Do not move files individually**; keep the folder structure intact:

```
termiclock
├─ termiclock.exe
└─ jre/
```
3. Double-click `termiclock.exe` to launch the clock.  
- The executable automatically uses the bundled JRE.  
- No Java installation is needed on the machine.

### With the JAR

```bash
java -jar TermiClock.jar [options]
```

### With the Windows Executable (if you already have jdk/jre installed)

```bash
TermiClock.exe [options]
```

### Available Options

| Option | Description |
|--------|-------------|
| `-p` | Display the clock once and exit |
| `-t` | Use the `hashtag` font |
| `-s` | Use the `star` font |
| `-c` | Use the `clock` font |
| `-d` | Display the date as well |
| `-h`, `--help` | Show help message |

### Examples

```bash
# Display the time once with hashtag font
java -jar TermiClock.jar -t -p

# Display time and date continuously with star font
java -jar TermiClock.jar -s -d

# Show help
java -jar TermiClock.jar --help
```

---

## 🛠️ Development

### Architecture

- **`org.termiclock.Main`** : Entry point, argument processing
- **`org.termiclock.clock.ClockProcess`** : Main display logic
- **`org.termiclock.util.JsonParser`** : Font JSON parsing

### Adding a New Font

1. Create JSON files for your digits in `src/main/resources/font/data/`
2. Create JSON files for separator characters in `src/main/resources/font/dots/`
3. Register the new type in `types.json`
4. Recompile the project

---

## 📦 Distribution

To distribute TermiClock:

1. Generate the JAR with integrated resources
2. Use **Launch4j** or **jpackage** to create a `.exe` (Windows only)
3. Ensure the `font/` resources are included in the archive

**Tested on:** Windows 10/11 with Java 17+

---

## 📝 License

This project is licensed under the **MIT License**. See the [LICENSE](LICENSE) file for more details.

---

## 🤝 Contributing

Contributions are welcome! Feel free to suggest improvements or new fonts.