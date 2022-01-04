#!/bin/sh
oc exec $1 -- solr create -c $2