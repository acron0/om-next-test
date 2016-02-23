(ns om-next-test.core
  (:require [goog.dom :as gdom]
            [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]
            [datascript.core :as d]
            ;;
            [om-next-test.data      :as data]
            [om-next-test.side      :as side]
            [om-next-test.app       :as app]
            [om-next-test.primary   :as primary]
            [om-next-test.secondary :as secondary]))

(enable-console-print!)

;;

(if-let [node (gdom/getElement "app")]
  (om/add-root! (data/make-reconciler) app/Main node))

(if-let [node (gdom/getElement "side")]
  (om/add-root! (data/make-reconciler) side/Main node))
