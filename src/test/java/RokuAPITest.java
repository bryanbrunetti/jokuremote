import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class RokuAPITests {
    @BeforeAll
    void setupMocks() {

    }
    @Test
    public static void discover() {
    /*
<?xml version="1.0" encoding="UTF-8" ?>
<device-info>
    <udn>29680000-800d-1067-80ee-d8313437a1f2</udn>
    <serial-number>YJ000P878574</serial-number>
    <device-id>CK4890878574</device-id>
    <advertising-id>6a7744dd-07f3-54b0-aeb1-b1eb3b17f70f</advertising-id>
    <vendor-name>Roku</vendor-name>
    <model-name>Roku Ultra</model-name>
    <model-number>4660X</model-number>
    <model-region>US</model-region>
    <is-tv>false</is-tv>
    <is-stick>false</is-stick>
    <supports-ethernet>true</supports-ethernet>
    <wifi-mac>d8:31:34:37:a1:f2</wifi-mac>
    <wifi-driver>realtek</wifi-driver>
    <ethernet-mac>d8:31:34:37:a1:f3</ethernet-mac>
    <network-type>wifi</network-type>
    <network-name>OSHOx</network-name>
    <friendly-device-name>Roku Ultra</friendly-device-name>
    <friendly-model-name>Roku Ultra</friendly-model-name>
    <default-device-name>Roku Ultra - YJ000P878574</default-device-name>
    <user-device-name>Roku Ultra</user-device-name>
    <user-device-location>Bedroom</user-device-location>
    <build-number>469.20E04807A</build-number>
    <software-version>9.2.0</software-version>
    <software-build>4807</software-build>
    <secure-device>true</secure-device>
    <language>en</language>
    <country>US</country>
    <locale>en_US</locale>
    <time-zone-auto>true</time-zone-auto>
    <time-zone>US/Eastern</time-zone>
    <time-zone-name>United States/Eastern</time-zone-name>
    <time-zone-tz>America/New_York</time-zone-tz>
    <time-zone-offset>-300</time-zone-offset>
    <clock-format>12-hour</clock-format>
    <uptime>5550370</uptime>
    <power-mode>PowerOn</power-mode>
    <supports-suspend>false</supports-suspend>
    <supports-find-remote>true</supports-find-remote>
    <find-remote-is-possible>true</find-remote-is-possible>
    <supports-audio-guide>true</supports-audio-guide>
    <supports-rva>true</supports-rva>
    <developer-enabled>false</developer-enabled>
    <keyed-developer-id/>
    <search-enabled>true</search-enabled>
    <search-channels-enabled>true</search-channels-enabled>
    <voice-search-enabled>true</voice-search-enabled>
    <notifications-enabled>true</notifications-enabled>
    <notifications-first-use>false</notifications-first-use>
    <supports-private-listening>true</supports-private-listening>
    <headphones-connected>false</headphones-connected>
    <supports-ecs-textedit>true</supports-ecs-textedit>
    <supports-ecs-microphone>true</supports-ecs-microphone>
    <supports-wake-on-wlan>false</supports-wake-on-wlan>
    <has-play-on-roku>true</has-play-on-roku>
    <has-mobile-screensaver>true</has-mobile-screensaver>
    <support-url>roku.com/support</support-url>
    <grandcentral-version>2.9.57</grandcentral-version>
    <trc-version>3.0</trc-version>
    <trc-channel-version>2.9.42</trc-channel-version>
    <davinci-version>2.8.20</davinci-version>
    <has-wifi-extender>false</has-wifi-extender>
    <has-wifi-5G-support>true</has-wifi-5G-support>
    <can-use-wifi-extender>true</can-use-wifi-extender>
</device-info>
http://192.168.86.28:8060
*/
    }
}
