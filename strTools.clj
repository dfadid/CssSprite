(ns NewCssSprite.strTools
   (:import (java.io File)))

(defn css-class-str
    "Convert the image path to a valid CSS class name"
    [image]
    (.replaceAll
      (.replaceAll (:path image) "(\\\\|/)" "")
      "(?i).png$"
      "")) 
 

(defn file-str 
  
  " taken from clojure contrib 
    Returns a java.io.File.  Replaces ~ at the start of the path 
    with the user.home system property."
  [& args]
  (let [#^String s (apply str args)
        s (if (.startsWith s "~")
            (str (System/getProperty "user.home")
                 File/separator (subs s 1))
            s)]
    (File. s)))