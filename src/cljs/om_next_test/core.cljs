(ns om-next-test.core
  (:require [goog.dom :as gdom]
            [om.next :as om :refer-macros [defui]]
            ;;
            [om-next-test.data      :as data]
            [om-next-test.side      :as side]
            [om-next-test.app       :as app]))

(if-let [node (gdom/getElement "app")]
  (om/add-root! (data/make-reconciler) app/Main node))

(if-let [node (gdom/getElement "side")]
  (om/add-root! (data/make-reconciler) side/Main node))
