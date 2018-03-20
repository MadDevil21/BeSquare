package org.academiadecodigo.haltistas.besquare.menu;

public class MenuController {

    private Port port;
    private Ip ip;

    MenuController() {
        ip = new Ip();
        port = new Port();
    }

    boolean isValidIP() {
        return ip.isValid();
    }

    void addDigitToIP(int digit) {
        ip.addDigit(digit);
    }

    void removeDigitToIP() {
        ip.removeDigit();
    }

    String getIP() {
        return ip.toString();
    }

    void addDigitToPort(int digit) {
        port.addDigit(digit);
    }

    void removeDigitToPort() {
        port.removeDigit();
    }

    boolean isValidPort() {
        return port.isValid();
    }

    String getPort() {
        return port.toString();
    }

    private class Ip {

        private String[] octet;
        private int index = 0;

        Ip() {
            octet = new String[]{"", "", "", ""};

        }

        void addDigit(int digit) {

            if (octet[index].length() == 3) {

                if (index == 3 || !isValidOctet()) {
                    return;
                }

                index++;
            }

            octet[index] = octet[index] + digit;

        }

        void removeDigit() {
            if (octet[index].isEmpty() || octet[index] == null) {

                if (index == 0) {
                    return;
                }
                index--;

            }

            octet[index] = octet[index].substring(0, octet[index].length() - 1);

        }

        private int getOctet() {
            return Integer.parseInt(octet[index].isEmpty() ? "0" : octet[index]);
        }

        boolean isValidOctet() {
            int octet = getOctet();
            return 0 <= octet && octet <= 255;

        }

        boolean isValid() {
            return isValidOctet() && index == 3;
        }

        @Override
        public String toString() {
            return ((octet[0].isEmpty() ? "000" : octet[0]) + "." +
                    (octet[1].isEmpty() ? "000" : octet[1]) + "." +
                    (octet[2].isEmpty() ? "000" : octet[2]) + "." +
                    (octet[3].isEmpty() ? "000" : octet[3]));
        }
    }

    private class Port {
        private String port;

        Port() {
            this.port = "";
        }

        void addDigit(int digit) {
            if (Integer.parseInt(this.port.isEmpty() ? "0" : this.port) > 65535) {
                return;
            }
            this.port += digit;
        }

        boolean isValid() {
            int port = Integer.parseInt(this.port.isEmpty() ? "0" : this.port);
            return (port >= 1024) && (port <= 65535);

        }

        private void removeDigit() {
            if (port.isEmpty()) {
                return;
            }
            port = port.substring(0, port.length() - 1);
        }

        @Override
        public String toString() {
            return port.isEmpty() ? "0000" : port;
        }

    }


}
