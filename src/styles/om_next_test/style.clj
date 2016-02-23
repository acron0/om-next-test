(ns om-next-test.style
  (:require [garden.def :refer [defstyles]]
            ;;
            [om-next-test.style.fonts      :as fonts]
            [om-next-test.style.icons      :as icons]
            [om-next-test.style.animations :as anims]
            [om-next-test.style.layout     :as layout]
            [om-next-test.style.login      :as login]
            [om-next-test.style.side       :as side]
            [om-next-test.style.app        :as app]
            [om-next-test.style.dashboard  :as dashboard]
            [om-next-test.style.shared     :as shared]
            ;;
            [om-next-test.ext-style.splitjs :as splitjs]))

(defstyles main
  (->>
   [fonts/style
    icons/style
    anims/style
    layout/style
    login/style
    side/style
    app/style
    dashboard/style
    shared/style
    ;;
    splitjs/style]
   ;;
   (reduce concat)
   (vec)))
