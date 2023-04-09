import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './panier.reducer';

export const PanierDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const panierEntity = useAppSelector(state => state.panier.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="panierDetailsHeading">
          <Translate contentKey="coopcycleApp.panier.detail.title">Panier</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{panierEntity.id}</dd>
          <dt>
            <span id="dateCreation">
              <Translate contentKey="coopcycleApp.panier.dateCreation">Date Creation</Translate>
            </span>
          </dt>
          <dd>
            {panierEntity.dateCreation ? <TextFormat value={panierEntity.dateCreation} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="dateModification">
              <Translate contentKey="coopcycleApp.panier.dateModification">Date Modification</Translate>
            </span>
          </dt>
          <dd>
            {panierEntity.dateModification ? (
              <TextFormat value={panierEntity.dateModification} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="statut">
              <Translate contentKey="coopcycleApp.panier.statut">Statut</Translate>
            </span>
          </dt>
          <dd>{panierEntity.statut}</dd>
          <dt>
            <span id="total">
              <Translate contentKey="coopcycleApp.panier.total">Total</Translate>
            </span>
          </dt>
          <dd>{panierEntity.total}</dd>
          <dt>
            <Translate contentKey="coopcycleApp.panier.produit">Produit</Translate>
          </dt>
          <dd>
            {panierEntity.produits
              ? panierEntity.produits.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {panierEntity.produits && i === panierEntity.produits.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>
            <Translate contentKey="coopcycleApp.panier.idClient">Id Client</Translate>
          </dt>
          <dd>{panierEntity.idClient ? panierEntity.idClient.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/panier" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/panier/${panierEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default PanierDetail;
