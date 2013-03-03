(ns NewCssSprite.writeCss
  (:use  NewCssSprite.strTools))



(defn write-it-css [ oFile image ]
  "Write a line of CSS code for a single image to a given output file"
  (let [css-class (str "#" (css-class-str image)) ;genetate the css class name from the file`s name added # 
        [img-width img-height] (map str (:dimensions image)) ; the dimenensions of the image
          pos-x(str (:x image))    ; the x position of the image in the sprite image
          pos-y(str (:y image))]   ; the y position of the image in the sprite image
  
    (.write oFile (str css-class " { width: " img-width " ; height: " img-height ";
                                  background-position: " pos-x "-"  pos-y "px ; } \n")))) 
 ;writing to the output file the data as a css code format

 
 
(defn write-it-html [ oFile image ]
   "Write a line of html code for a single image to a given output file"
  (let [img-name(css-class-str image) 
        path (:path image) 
        [img-width img-height] (map str (:dimensions image)) ; the dimenensions of the image
        pos-x(str (:x image))   ; the x position of the image in the sprite image
        pos-y(str (:y image))]  ; the y position of the image in the sprite image
  
    (.write oFile (str "<img src =\"" path  "\" style = \"width: " img-width "px ;
                        height: " img-height "px;  position: absolute;  top: " pos-y "; left: "  pos-x "\" > \n"))))
 ;writing to the output file the data as html code format



(defn writeToFile
  "Generate a CSS file and an HTML file given the images and their coordinates.
   going over the sequence of images data and for each one write it`s data to the output file "
  [images ]
  (with-open [wrtr1 (clojure.java.io/writer (file-str "sprite.css"))] ;open the given output file
    (.write wrtr1 (str "/* CSS Sprite File    " (java.util.Date.) " ...*/\n\n" )) ;writing a header 
       (doseq[image images] (write-it-css  wrtr1 image  )))
  
  (with-open [wrtr2 (clojure.java.io/writer (file-str "sprite.html"))]
    (.write wrtr2 (str "<body bgcolor=\"black\">")) ; define the background color as black 
       (doseq[image images] (write-it-html  wrtr2 image ))
    (.write wrtr2 (str"</body>"))))
